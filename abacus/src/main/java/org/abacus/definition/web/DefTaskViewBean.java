package org.abacus.definition.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.definition.shared.entity.DefTypeEntity;
import org.abacus.organization.core.handler.OrganizationHandler;
import org.abacus.organization.shared.entity.OrganizationEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DefTaskViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{defTaskHandler}")
	private DefTaskHandler defTaskService;

	@ManagedProperty(value = "#{organizationHandler}")
	private OrganizationHandler organizationHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private DefTypeEntity selType;

	private DefTaskEntity selTask;
	private List<DefTaskEntity> taskList;

	private OrganizationEntity rootOrganization;

	@PostConstruct
	public void init() {
		rootOrganization = sessionInfoHelper.currentRootOrganization();
	}

	public void setSelType(DefTypeEntity selType) {
		this.selType = selType;
		findTypeTask();
	}

	public void taskRowSelectListener() {
	}

	public void saveTask() {
		if (selTask.isNew()) {
			jsfMessageHelper.addInfo("createSuccessful","Görev");
		} else {
			jsfMessageHelper.addInfo("updateSuccessful","Görev");
		}
		defTaskService.saveTaskEntity(selTask);
		findTypeTask();
	}

	public void deleteTask() {
		if (!selTask.isNew()) {
			defTaskService.deleteTaskEntity(selTask);
			jsfMessageHelper.addInfo("deleteSuccessful","Görev");
		}
		findTypeTask();
	}

	public void createTask() {
		selTask = new DefTaskEntity();
		selTask.setType(selType);
		selTask.setOrganization(rootOrganization);
	}

	public void findTypeTask() {
		createTask();
		taskList = null;
		if (selType != null) {
			taskList = defTaskService.getTaskList(rootOrganization, selType.getTypeEnum());
		} else {
			taskList = new ArrayList<DefTaskEntity>();
		}
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public DefTaskEntity getSelTask() {
		return selTask;
	}

	public void setSelTask(DefTaskEntity selTask) {
		if (selTask != null) {
			this.selTask = selTask;
		}
	}

	public DefTaskHandler getDefTaskService() {
		return defTaskService;
	}

	public void setDefTaskService(DefTaskHandler defTaskService) {
		this.defTaskService = defTaskService;
	}

	public List<DefTaskEntity> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<DefTaskEntity> taskList) {
		this.taskList = taskList;
	}

	public OrganizationHandler getOrganizationHandler() {
		return organizationHandler;
	}

	public void setOrganizationHandler(OrganizationHandler organizationHandler) {
		this.organizationHandler = organizationHandler;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

}
