package org.abacus.transaction.web;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.transaction.core.handler.ReqConfirmationHandler;
import org.abacus.transaction.core.handler.ReqOfferHandler;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.event.CreateOfferEvent;
import org.abacus.transaction.shared.event.OfferCreatedEvent;
import org.abacus.transaction.shared.event.OfferUpdatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.event.UpdateOfferEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.abacus.user.core.persistance.repository.UserOrganizationRepository;
import org.springframework.util.CollectionUtils;

//TODO Request guncellenmek istendiginde teklif durumu kontrol edilmelidir.
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CrudPurchaseDocumentViewBean implements Serializable {

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

	private Boolean showDocument = true;

	@PostConstruct
	private void init() {

		String documentId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("document");
		
		this.vendor = sessionInfoHelper.currentUser().getVendor();
		if (vendor == null) {
			this.showDocument = false;
			jsfMessageHelper.addWarn("noVendorSelected");
		}

		this.findDocument(Long.valueOf(documentId));

	}
	
	public void reviewDocument(){
		
	}
	
	public void partiallyDoneDocument(){
		
	}
	
	public void backToReviewDocument(){
		
	}
	
	public void confirmDocument(){
		
	}
	
	public ReqDetailOfferEntity vendorOffer(ReqDetailEntity detail) {
		Set<ReqDetailOfferEntity> offerSet = detail.getOfferSet();

		ReqDetailOfferEntity offer = null;
		if (!CollectionUtils.isEmpty(offerSet)) {
			for (ReqDetailOfferEntity coffer : offerSet) {
				if (coffer.getVendorItem().getId().equals(vendor.getId())) {
					offer = coffer;
				}
			}
		}

		if (offer == null) {
			offer = this.initNewOffer(detail);
		}

		return offer;

	}

	private void findDocument(Long documentId) {
		TraDocumentSearchCriteria traDocumentSearchCriteria = new TraDocumentSearchCriteria(documentId);

		DefTaskEntity purchaseTaskList = taskRepository.getTaskList(sessionInfoHelper.currentRootOrganization(), EnumList.DefTypeEnum.REQ_IO_P).get(0);
		traDocumentSearchCriteria.setDocTask(purchaseTaskList);

		ReadDocumentEvent<ReqDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<ReqDocumentEntity>(traDocumentSearchCriteria, sessionInfoHelper.currentOrganization(), sessionInfoHelper.currentFiscalYear()));
		if (CollectionUtils.isEmpty(readDocumentEvent.getDocumentList())) {
			document = null;
		} else {
			document = readDocumentEvent.getDocumentList().get(0);
			ReadDetailEvent<ReqDetailEntity> readDetailEvent = transactionHandler.readDetailList(new RequestReadDetailEvent<ReqDetailEntity>(document.getId()));
			detailList = readDetailEvent.getDetails();
		}
	}

	private ReqDetailOfferEntity initNewOffer(ReqDetailEntity detail) {
		ReqDetailOfferEntity offer = new ReqDetailOfferEntity();
		offer.setDetail(detail);
		offer.setVendorItem(vendor);
		return offer;
	}

	public void offerSelected(ReqDetailEntity detail, ReqDetailOfferEntity offer) {
		this.selectedDetail = detail;
		this.selectedOffer = offer;
	}

	public void deleteOffer(ReqDetailOfferEntity offer) {
		try {
			reqOfferHandler.deleteOffer(offer);
			this.findDocument(document.getId());
			jsfMessageHelper.addInfo("deleteSuccessful", "Teklif");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void saveOffer() {
		try {
			OfferCreatedEvent createdEvent = reqOfferHandler.saveOffer(new CreateOfferEvent(this.selectedOffer,sessionInfoHelper.currentUserName()));
			this.findDocument(document.getId());
			jsfMessageHelper.addInfo("createSuccessful", "Teklif");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void updateOffer() {
		try {
			OfferUpdatedEvent updatedEvent = reqOfferHandler.updateOffer(new UpdateOfferEvent(this.selectedOffer,sessionInfoHelper.currentUserName()));
			this.findDocument(document.getId());
			jsfMessageHelper.addInfo("updateSuccessful", "Teklif");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
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

}
