package org.abacus.definition.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefValueHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.primefaces.context.RequestContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DefValTableViewBean implements Serializable {

	@ManagedProperty(value = "#{defValueHandler}")
	private DefValueHandler defValueHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private List<DefValueEntity> categoryList;

	@PostConstruct
	public void init() {
		String rootOrganization = sessionInfoHelper.currentRootOrganizationId();
		String type= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
		this.categoryList = defValueHandler.getValueList(rootOrganization, EnumList.DefTypeEnum.valueOf(type)); 
	}

	public void selectValueFromDialog(DefValueEntity category) {
		RequestContext.getCurrentInstance().closeDialog(category);
	}

	public DefValueHandler getDefValueHandler() {
		return defValueHandler;
	}

	public void setDefValueHandler(DefValueHandler defValueHandler) {
		this.defValueHandler = defValueHandler;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public List<DefValueEntity> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<DefValueEntity> categoryList) {
		this.categoryList = categoryList;
	}

}
