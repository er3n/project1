package org.abacus.definition.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
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

}
