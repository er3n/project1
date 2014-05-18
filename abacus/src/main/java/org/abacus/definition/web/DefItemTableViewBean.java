package org.abacus.definition.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefItemHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.definition.web.model.ItemDataModel;
import org.primefaces.context.RequestContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DefItemTableViewBean implements Serializable {

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private ItemSearchCriteria searchCriteria;

	private ItemDataModel itemLazyModel;

	private DefItemEntity selectedItem;

	@ManagedProperty(value = "#{defItemHandler}")
	private DefItemHandler itemHandler;

	@PostConstruct
	public void init() {
		this.initParameters();
		itemLazyModel = new ItemDataModel(searchCriteria);
	}

	private void initParameters() {
		String currentOrganization = sessionInfoHelper.currentRootOrganizationId();
		String itemTypeStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
		String itemClassStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("clazz");
		EnumList.DefTypeEnum type = EnumList.DefTypeEnum.valueOf(itemTypeStr);
		EnumList.DefItemClassEnum clazz = EnumList.DefItemClassEnum.valueOf(itemClassStr);
		searchCriteria = new ItemSearchCriteria(currentOrganization, type, clazz);
	}

	public void itemSelected(DefItemEntity item) {
		ReadItemEvent readItemEvent = itemHandler.findItem(new RequestReadItemEvent(selectedItem.getId()));
		selectedItem = readItemEvent.getItem();
		RequestContext.getCurrentInstance().closeDialog(selectedItem);
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public ItemSearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(ItemSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
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

	public DefItemHandler getItemHandler() {
		return itemHandler;
	}

	public void setItemHandler(DefItemHandler itemHandler) {
		this.itemHandler = itemHandler;
	}

}
