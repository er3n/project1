package org.abacus.definition.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefItemHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.definition.web.model.ItemDataModel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{itemHandler}")
	private DefItemHandler itemHandler;

	private ItemSearchCriteria itemSearchCriteria;

	private ItemDataModel itemLazyModel;

	private DefItemEntity selectedItem;

	@PostConstruct
	public void init() {
		this.initParameters();
		itemLazyModel = new ItemDataModel(itemSearchCriteria);
	}

	private void initParameters() {
		String currentOrganization = sessionInfoHelper
				.currentRootOrganizationId();
		String itemTypeStr = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("type");
		String itemClassStr = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("class");
		EnumList.DefItemTypeEnum type = EnumList.DefItemTypeEnum
				.valueOf(itemTypeStr);
		EnumList.DefItemClassEnum clazz = EnumList.DefItemClassEnum
				.valueOf(itemClassStr);
		itemSearchCriteria = new ItemSearchCriteria(currentOrganization, type,
				clazz);
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

	public DefItemHandler getItemHandler() {
		return itemHandler;
	}

	public void setItemHandler(DefItemHandler itemHandler) {
		this.itemHandler = itemHandler;
	}

	public ItemSearchCriteria getItemSearchCriteria() {
		return itemSearchCriteria;
	}

	public void setItemSearchCriteria(ItemSearchCriteria itemSearchCriteria) {
		this.itemSearchCriteria = itemSearchCriteria;
	}

	public ItemDataModel getItemLazyModel() {
		return itemLazyModel;
	}

	public void setItemLazyModel(ItemDataModel itemLazyModel) {
		this.itemLazyModel = itemLazyModel;
	}

	public DefItemEntity getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(DefItemEntity selectedItem) {
		this.selectedItem = selectedItem;
	}

}
