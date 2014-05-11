package org.abacus.definition.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefValueHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.primefaces.context.RequestContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CategoryTableViewBean implements Serializable {

	@ManagedProperty(value = "#{defValueHandler}")
	private DefValueHandler defValueHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private List<DefValueEntity> categoryList;

	@PostConstruct
	public void init() {
		String rootOrganization = sessionInfoHelper.currentRootOrganizationId();
		String typ = EnumList.DefItemClassEnum.STK_M.name();
		this.categoryList = defValueHandler.getValueList(rootOrganization, "VAL_BESIN");
	}

	public void selectCategoryFromDialog(DefValueEntity category) {
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
