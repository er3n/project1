package org.abacus.transaction.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.transaction.core.handler.ReqConfirmationHandler;
import org.abacus.transaction.core.handler.ReqOfferHandler;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.ConfirmDocumentEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.event.UpdateSelectedOfferEvent;
import org.abacus.transaction.shared.holder.ReqPurcVendorHolder;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.abacus.user.core.persistance.repository.UserOrganizationRepository;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CrudPurchDecDocumentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	@ManagedProperty(value = "#{reqTransactionHandler}")
	private TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> transactionHandler;

	@ManagedProperty(value = "#{defTaskHandler}")
	private DefTaskHandler taskRepository;

	@ManagedProperty(value = "#{reqConfirmationHandler}")
	private ReqConfirmationHandler reqConfirmationHandler;

	@ManagedProperty(value = "#{userOrganizationRepository}")
	private UserOrganizationRepository userOrgRepo;

	@ManagedProperty(value = "#{reqOfferHandler}")
	private ReqOfferHandler reqOfferHandler;

	private ReqDocumentEntity document;

	private List<ReqDetailEntity> detailList;

	private DefItemEntity vendor;

	private ReqDetailEntity selectedDetail;

	private ReqDetailOfferEntity selectedOffer;

	private List<ReqPurcVendorHolder> choosenVendors;

	private Boolean showDocument = true;

	@PostConstruct
	private void init() {

		String documentId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("document");

		this.findDocument(Long.valueOf(documentId));

	}

	public void offerSelected(ReqDetailEntity detail, ReqDetailOfferEntity offer) {
		reqOfferHandler.updateSelectedOffer(new UpdateSelectedOfferEvent(detail, offer, sessionInfoHelper.currentUserName()));
		this.findDocument(document.getId());
		jsfMessageHelper.addInfo("updateSuccessful", "Teklif");
	}

	private void findDocument(Long documentId) {
		TraDocumentSearchCriteria traDocumentSearchCriteria = new TraDocumentSearchCriteria(documentId);

		DefTaskEntity purchaseTaskList = taskRepository.getTaskList(sessionInfoHelper.currentOrganization(), EnumList.DefTypeEnum.REQ_IO_P).get(0);
		traDocumentSearchCriteria.setDocTask(purchaseTaskList);

		ReadDocumentEvent<ReqDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<ReqDocumentEntity>(traDocumentSearchCriteria, sessionInfoHelper.currentOrganization(), sessionInfoHelper.currentFiscalYear()));
		if (CollectionUtils.isEmpty(readDocumentEvent.getDocumentList())) {
			document = null;
		} else {
			document = readDocumentEvent.getDocumentList().get(0);
			ReadDetailEvent<ReqDetailEntity> readDetailEvent = transactionHandler.readDetailList(new RequestReadDetailEvent<ReqDetailEntity>(document.getId()));
			detailList = readDetailEvent.getDetails();
		}
		
		if(this.document.getRequestStatus().equals(EnumList.RequestStatus.PARTIALLY)){
			this.findChoosenVendors();
		}
		
	}

	public void reviewDocument() {
		try {
			reqConfirmationHandler.reviewDocument(this.document, sessionInfoHelper.currentUserName());
			this.findDocument(this.document.getId());
			jsfMessageHelper.addInfo("updateSuccessful", "Döküman");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void partiallyDoneDocument() {
		try {
			reqConfirmationHandler.partiallyDoneDocument(this.document, sessionInfoHelper.currentUserName());
			this.findDocument(this.document.getId());
			jsfMessageHelper.addInfo("updateSuccessful", "Döküman");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void backToReviewDocument() {
		try {
			reqConfirmationHandler.backToReviewDocument(this.document, sessionInfoHelper.currentUserName());
			this.findDocument(this.document.getId());
			jsfMessageHelper.addInfo("updateSuccessful", "Döküman");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void backToRequestDocument() {
		try {
			reqConfirmationHandler.backToRequestDocument(this.document, sessionInfoHelper.currentUserName());
			this.findDocument(this.document.getId());
			jsfMessageHelper.addInfo("updateSuccessful", "Döküman");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void confirmDocument(ReqPurcVendorHolder vendorHolder) {
		try { 
			FiscalPeriodEntity period2 = sessionInfoHelper.getFiscalPeriod(this.document.getDocDate());
			StkDocumentEntity stkDocument = reqConfirmationHandler.confirmPartialDocument(new ConfirmDocumentEvent(this.document, period2, vendorHolder.getVendor()));
			this.findDocument(this.document.getId());
			jsfMessageHelper.addInfo("confirmedWithDocumentNo", stkDocument.getDocNo());
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void findChoosenVendors() {
		this.choosenVendors = reqOfferHandler.findChoosenVendors(this.document.getId());
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public JsfDialogHelper getJsfDialogHelper() {
		return jsfDialogHelper;
	}

	public void setJsfDialogHelper(JsfDialogHelper jsfDialogHelper) {
		this.jsfDialogHelper = jsfDialogHelper;
	}

	public TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public DefTaskHandler getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskHandler taskRepository) {
		this.taskRepository = taskRepository;
	}

	public ReqConfirmationHandler getReqConfirmationHandler() {
		return reqConfirmationHandler;
	}

	public void setReqConfirmationHandler(ReqConfirmationHandler reqConfirmationHandler) {
		this.reqConfirmationHandler = reqConfirmationHandler;
	}

	public UserOrganizationRepository getUserOrgRepo() {
		return userOrgRepo;
	}

	public void setUserOrgRepo(UserOrganizationRepository userOrgRepo) {
		this.userOrgRepo = userOrgRepo;
	}

	public ReqDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(ReqDocumentEntity document) {
		this.document = document;
	}

	public List<ReqDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ReqDetailEntity> detailList) {
		this.detailList = detailList;
	}

	public DefItemEntity getVendor() {
		return vendor;
	}

	public void setVendor(DefItemEntity vendor) {
		this.vendor = vendor;
	}

	public ReqOfferHandler getReqOfferHandler() {
		return reqOfferHandler;
	}

	public void setReqOfferHandler(ReqOfferHandler reqOfferHandler) {
		this.reqOfferHandler = reqOfferHandler;
	}

	public ReqDetailEntity getSelectedDetail() {
		return selectedDetail;
	}

	public void setSelectedDetail(ReqDetailEntity selectedDetail) {
		this.selectedDetail = selectedDetail;
	}

	public ReqDetailOfferEntity getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(ReqDetailOfferEntity selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public Boolean getShowDocument() {
		return showDocument;
	}

	public void setShowDocument(Boolean showDocument) {
		this.showDocument = showDocument;
	}

	public List<ReqPurcVendorHolder> getChoosenVendors() {
		return choosenVendors;
	}

	public void setChoosenVendors(List<ReqPurcVendorHolder> choosenVendors) {
		this.choosenVendors = choosenVendors;
	}

}
