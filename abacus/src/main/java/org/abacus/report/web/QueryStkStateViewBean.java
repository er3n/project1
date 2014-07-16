package org.abacus.report.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.report.core.handler.ReportHandler;
import org.abacus.report.shared.event.ReadReportEvent;
import org.abacus.report.shared.event.RequestReadReportEvent;
import org.abacus.report.shared.holder.ReportSearchCriteria;
import org.abacus.transaction.shared.entity.StkDetailEntity;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class QueryStkStateViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	@ManagedProperty(value = "#{defTaskHandler}")
	private DefTaskHandler taskRepository;

	@ManagedProperty(value = "#{reportHandler}")
	private ReportHandler reportHandler;

	private ReportSearchCriteria reportSearchCriteria;
	private List<StkDetailEntity> searchResultList;
	private Boolean showDocument = true; 
	private List<DefTaskEntity> stkTaskList;
	
	@PostConstruct
	private void init() {
		reportSearchCriteria = new ReportSearchCriteria();
		reportSearchCriteria.setOrganization(sessionInfoHelper.currentOrganization());
		reportSearchCriteria.setFiscalYear(sessionInfoHelper.currentUser().getSelectedFiscalYear());
		this.showDocument = sessionInfoHelper.currentUser().getSelectedFiscalYear() != null;
		jsfMessageHelper.addWarn("noFiscalYearDefined");
		stkTaskList = taskRepository.getTaskList(sessionInfoHelper.currentOrganization(), EnumList.DefTypeEnum.STK);
	}

	public void searchResult() {
		RequestReadReportEvent requestReadReportEvent = new RequestReadReportEvent(reportSearchCriteria);
		ReadReportEvent<StkDetailEntity> readReportEvent = reportHandler.getStkState(requestReadReportEvent);
		searchResultList = readReportEvent.getDetailList();
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

	public Boolean getShowDocument() {
		return showDocument;
	}

	public void setShowDocument(Boolean showDocument) {
		this.showDocument = showDocument;
	}
	
	public List<DefTaskEntity> getStkTaskList() {
		return stkTaskList;
	}

	public void setStkTaskList(List<DefTaskEntity> stkTaskList) {
		this.stkTaskList = stkTaskList;
	}

	public DefTaskHandler getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskHandler taskRepository) {
		this.taskRepository = taskRepository;
	}

	public ReportHandler getReportHandler() {
		return reportHandler;
	}

	public void setReportHandler(ReportHandler reportHandler) {
		this.reportHandler = reportHandler;
	}

	public List<StkDetailEntity> getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(List<StkDetailEntity> searchResultList) {
		this.searchResultList = searchResultList;
	}

	public ReportSearchCriteria getReportSearchCriteria() {
		return reportSearchCriteria;
	}

	public void setReportSearchCriteria(ReportSearchCriteria reportSearchCriteria) {
		this.reportSearchCriteria = reportSearchCriteria;
	}

}
