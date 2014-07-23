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
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.report.core.handler.TraReportHandler;
import org.abacus.report.shared.event.ReadReportEvent;
import org.abacus.report.shared.event.RequestReadReportEvent;
import org.abacus.report.shared.holder.ReportSearchCriteria;
import org.abacus.transaction.shared.entity.FinDetailEntity;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class QueryFinDetailViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	@ManagedProperty(value = "#{defTaskHandler}")
	private DefTaskHandler taskRepository;

	@ManagedProperty(value = "#{traReportHandler}")
	private TraReportHandler reportHandler;

	private ReportSearchCriteria reportSearchCriteria;
	private List<FinDetailEntity> searchResultList;
	private Boolean showDocument = true;
	private List<DefTaskEntity> finTaskList;
	
	@PostConstruct
	private void init() {
		reportSearchCriteria = new ReportSearchCriteria();
		reportSearchCriteria.setOrganization(sessionInfoHelper.currentOrganization());
		reportSearchCriteria.setFiscalYear(sessionInfoHelper.currentUser().getSelectedFiscalYear());
		this.showDocument = sessionInfoHelper.currentUser().getSelectedFiscalYear() != null;
		jsfMessageHelper.addWarn("noFiscalYearDefined");
		finTaskList = taskRepository.getTaskList(sessionInfoHelper.currentOrganization(), EnumList.DefTypeEnum.FIN);
	}

	public void searchResult() {
		RequestReadReportEvent requestReadReportEvent = new RequestReadReportEvent(reportSearchCriteria);
		ReadReportEvent<FinDetailEntity> readReportEvent = reportHandler.getFinDetail(requestReadReportEvent);
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
	
	public List<DefTaskEntity> getFinTaskList() {
		return finTaskList;
	}

	public void setFinTaskList(List<DefTaskEntity> finTaskList) {
		this.finTaskList = finTaskList;
	}

	public DefTaskHandler getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskHandler taskRepository) {
		this.taskRepository = taskRepository;
	}

	public TraReportHandler getReportHandler() {
		return reportHandler;
	}

	public void setReportHandler(TraReportHandler reportHandler) {
		this.reportHandler = reportHandler;
	}

	public List<FinDetailEntity> getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(List<FinDetailEntity> searchResultList) {
		this.searchResultList = searchResultList;
	}

	public ReportSearchCriteria getReportSearchCriteria() {
		return reportSearchCriteria;
	}

	public void setReportSearchCriteria(ReportSearchCriteria reportSearchCriteria) {
		this.reportSearchCriteria = reportSearchCriteria;
	}

}
