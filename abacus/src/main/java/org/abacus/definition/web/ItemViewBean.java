package org.abacus.definition.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefItemHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.definition.web.model.ItemDataModel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{defItemHandler}")
	private DefItemHandler itemHandler;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	private ItemSearchCriteria itemSearchCriteria;

	private ItemDataModel itemLazyModel;

	private DefItemEntity selectedItem;

	@PostConstruct
	public void init() {
		this.initParameters();
		itemLazyModel = new ItemDataModel(itemSearchCriteria);
	}

	public void itemSelected() {
		ReadItemEvent readItemEvent = itemHandler.findItem(new RequestReadItemEvent(selectedItem.getId()));
		selectedItem = readItemEvent.getItem();
	}

	private void initParameters() {
		String currentOrganization = sessionInfoHelper.currentRootOrganizationId();
		String itemTypeStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
		String itemClassStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("class");
		EnumList.DefTypeEnum type = EnumList.DefTypeEnum.valueOf(itemTypeStr);
		EnumList.DefItemClassEnum clazz = EnumList.DefItemClassEnum.valueOf(itemClassStr);
		itemSearchCriteria = new ItemSearchCriteria(currentOrganization, type, clazz);
	}

	public void chooseCategory() {
		jsfDialogHelper.openDefValueDialog(EnumList.DefTypeEnum.VAL_BESIN);
	}

	public void onCategoryChosen(SelectEvent event) {
		DefValueEntity category = (DefValueEntity) event.getObject();
		selectedItem.setCategory(category);
	}

	public void clearCategory() {
		selectedItem.setCategory(null);
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

	public JsfDialogHelper getJsfDialogHelper() {
		return jsfDialogHelper;
	}

	public void setJsfDialogHelper(JsfDialogHelper jsfDialogHelper) {
		this.jsfDialogHelper = jsfDialogHelper;
	}

}
