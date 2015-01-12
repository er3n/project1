package org.abacus.definition.web;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefItemHandler;
import org.abacus.definition.core.handler.DefUnitHandler;
import org.abacus.definition.core.handler.DefValueHandler;
import org.abacus.definition.shared.ItemAlreadyExistsException;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefItemProductEntity;
import org.abacus.definition.shared.entity.DefItemUnitEntity;
import org.abacus.definition.shared.entity.DefTypeEntity;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.abacus.definition.shared.entity.DefUnitGroupEntity;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.abacus.definition.shared.event.CreateItemEvent;
import org.abacus.definition.shared.event.CreateItemProductEvent;
import org.abacus.definition.shared.event.DeleteItemProductEvent;
import org.abacus.definition.shared.event.ItemCreatedEvent;
import org.abacus.definition.shared.event.ItemProductCreatedEvent;
import org.abacus.definition.shared.event.ItemProductDeletedEvent;
import org.abacus.definition.shared.event.ItemProductUpdatedEvent;
import org.abacus.definition.shared.event.ItemUpdatedEvent;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.event.UpdateItemEvent;
import org.abacus.definition.shared.event.UpdateItemProductEvent;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.definition.web.model.ItemDataModel;
import org.abacus.organization.shared.entity.OrganizationEntity;

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

	@ManagedProperty(value = "#{defUnitHandler}")
	private DefUnitHandler defUnitHandler;

	@ManagedProperty(value = "#{defValueHandler}")
	private DefValueHandler defValueHandler;

	private ItemDataModel itemLazyModel;

	private DefItemEntity selectedItem;

	private List<DefUnitGroupEntity> allUnitGroupList;

	private EnumList.DefTypeEnum type;

	private EnumList.DefItemClassEnum clazz;

	private Set<DefUnitCodeEntity> selectedUnitGroupsUnitCodeSet;

	private Set<DefUnitCodeEntity> selectedUnitGroupsSelectedUnitCodeSet;

	private DefItemProductEntity selectedItemProduct;

	private Boolean displayProductInfo;

	private OrganizationEntity rootOrganization;

	@PostConstruct
	public void init() {
		rootOrganization = sessionInfoHelper.currentOrganization().getRootOrganization();
		
		this.initParameters();
		itemLazyModel = new ItemDataModel(itemSearchCriteria);
		this.initUnitGroups();

//		if (displayProductInfo) {
//			allReceiptList = defValueHandler.getValueList(sessionInfoHelper.currentRootOrganizationId(), EnumList.DefTypeEnum.VAL_RECEIPT);
//		}

	}

	public void initNewProduct() {

	}

	public void clear() {
		allUnitGroupList = null;
		selectedItem = null;
		this.init();
	}

	public void updateItem() {
		try {
			String userUpdated = sessionInfoHelper.currentUserName();
			ItemUpdatedEvent updatedEvent = itemHandler.updateItem(new UpdateItemEvent(selectedItem, selectedUnitGroupsSelectedUnitCodeSet, userUpdated, rootOrganization));
			this.itemSelected();
			jsfMessageHelper.addInfo("updateSuccessful");
		} catch (ItemAlreadyExistsException e) {
			jsfMessageHelper.addException(e);
		}
	}

	public void unitGroupSelected() {
		if (selectedItem.getUnitGroup() == null) {
			selectedUnitGroupsUnitCodeSet = null;
			return;
		}
//		List<DefUnitCodeEntity> selectedUnitGroupsUnitCodeList = defUnitHandler.getUnitCodeList(selectedItem.getUnitGroup().getId());
//		selectedUnitGroupsUnitCodeSet = new LinkedHashSet<>(selectedUnitGroupsUnitCodeList);
		for (DefUnitGroupEntity defUnitGroupEntity : allUnitGroupList) {
			if (defUnitGroupEntity.getId().equals(selectedItem.getUnitGroup().getId())){
				selectedUnitGroupsUnitCodeSet = defUnitGroupEntity.getUnitCodeList();
				return;
			}
		}
	}

	public void newItem() {
			try {
				String createdUser = sessionInfoHelper.currentUserName();
				ItemCreatedEvent createdEvent = itemHandler.newItem(new CreateItemEvent(selectedItem, selectedUnitGroupsSelectedUnitCodeSet, createdUser));
				selectedItem = null;
				jsfMessageHelper.addInfo("craeteSuccessful");
			} catch (ItemAlreadyExistsException e) {
				jsfMessageHelper.addException(e);
			}
			
	}

	public void newItemProduct() {
		String createdUser = sessionInfoHelper.currentUserName();
		ItemProductCreatedEvent createdEvent = itemHandler.newItemProduct(new CreateItemProductEvent(selectedItemProduct, createdUser));
		selectedItemProduct = null;
		this.itemSelected();
		jsfMessageHelper.addInfo("craeteSuccessful");
	}

	public void updateItemProduct() {
		String userUpdated = sessionInfoHelper.currentUserName();
		ItemProductUpdatedEvent updatedEvent = itemHandler.updateItemProduct(new UpdateItemProductEvent(selectedItemProduct, userUpdated));
		selectedItemProduct = null;

		this.itemSelected();

		jsfMessageHelper.addInfo("updateSuccessful");
	}

	public void deleteItemProduct(DefItemProductEntity prodItem) {
		if (prodItem != null){
			this.selectedItemProduct = prodItem;
			System.out.println(prodItem.getMaterialItem().getName());
			ItemProductDeletedEvent deletedEvent = itemHandler.deleteItemProduct(new DeleteItemProductEvent(this.selectedItemProduct));
			jsfMessageHelper.addInfo("deleteSuccessful");
			selectedItem.getItemProductSet().remove(this.selectedItemProduct);
			this.selectedItemProduct = null;
		}
		
	}
	
	public void itemSelected() {
		ReadItemEvent readItemEvent = itemHandler.findItem(new RequestReadItemEvent(selectedItem.getId()));
		selectedItem = readItemEvent.getItem();

		selectedUnitGroupsSelectedUnitCodeSet = new HashSet<>();
		for (DefItemUnitEntity itemUnit : selectedItem.getItemUnitSet()) {
			selectedUnitGroupsSelectedUnitCodeSet.add(itemUnit.getUnitCode());
		}

		this.itemChange();

		this.unitGroupSelected();
	}

	public void newItemSelected() {
		selectedItem = new DefItemEntity();
		selectedItem.setOrganization(rootOrganization);
		selectedItem.setType(new DefTypeEntity(type.name()));
		selectedItem.setItemClass(clazz);
		selectedItem.setCategory(new DefValueEntity());

		this.itemChange();

		this.unitGroupSelected();
	}

	public void newItemProductSelected() {
		selectedItemProduct = new DefItemProductEntity();
		selectedItemProduct.setItem(selectedItem);
	}

	private void itemChange() {
		selectedItemProduct = null;
	}

	private void initParameters() {
		String itemTypeStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
		String itemClassStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("class");
		type = EnumList.DefTypeEnum.valueOf(itemTypeStr);
		clazz = itemClassStr==null?null:EnumList.DefItemClassEnum.valueOf(itemClassStr);
		itemSearchCriteria = new ItemSearchCriteria(rootOrganization, type, clazz);
		displayProductInfo = EnumList.DefTypeEnum.ITM_SR_ST.equals(type) && EnumList.DefItemClassEnum.STK_P.equals(clazz);
	}

	private void initUnitGroups() {
		this.allUnitGroupList = defUnitHandler.getUnitGroupList(rootOrganization.getId());
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

	public DefUnitHandler getDefUnitHandler() {
		return defUnitHandler;
	}

	public void setDefUnitHandler(DefUnitHandler defUnitHandler) {
		this.defUnitHandler = defUnitHandler;
	}

	public List<DefUnitGroupEntity> getAllUnitGroupList() {
		return allUnitGroupList;
	}

	public void setAllUnitGroupList(List<DefUnitGroupEntity> allUnitGroupList) {
		this.allUnitGroupList = allUnitGroupList;
	}

	public Set<DefUnitCodeEntity> getSelectedUnitGroupsUnitCodeSet() {
		return selectedUnitGroupsUnitCodeSet;
	}

	public void setSelectedUnitGroupsUnitCodeSet(Set<DefUnitCodeEntity> selectedUnitGroupsUnitCodeSet) {
		this.selectedUnitGroupsUnitCodeSet = selectedUnitGroupsUnitCodeSet;
	}

	public Set<DefUnitCodeEntity> getSelectedUnitGroupsSelectedUnitCodeSet() {
		return selectedUnitGroupsSelectedUnitCodeSet;
	}

	public void setSelectedUnitGroupsSelectedUnitCodeSet(Set<DefUnitCodeEntity> selectedUnitGroupsSelectedUnitCodeSet) {
		this.selectedUnitGroupsSelectedUnitCodeSet = selectedUnitGroupsSelectedUnitCodeSet;
	}

	public DefItemProductEntity getSelectedItemProduct() {
		return selectedItemProduct;
	}

	public void setSelectedItemProduct(DefItemProductEntity selectedItemProduct) {
		this.selectedItemProduct = selectedItemProduct;
	}

	public Boolean getDisplayProductInfo() {
		return displayProductInfo;
	}

	public void setDisplayProductInfo(Boolean displayProductInfo) {
		this.displayProductInfo = displayProductInfo;
	}

	public EnumList.DefTypeEnum getType() {
		return type;
	}

	public void setType(EnumList.DefTypeEnum type) {
		this.type = type;
	}

	public EnumList.DefItemClassEnum getClazz() {
		return clazz;
	}

	public void setClazz(EnumList.DefItemClassEnum clazz) {
		this.clazz = clazz;
	}

	public DefValueHandler getDefValueHandler() {
		return defValueHandler;
	}

	public void setDefValueHandler(DefValueHandler defValueHandler) {
		this.defValueHandler = defValueHandler;
	}

//	public List<DefValueEntity> getAllReceiptList() {
//		return allReceiptList;
//	}
//
//	public void setAllReceiptList(List<DefValueEntity> allReceiptList) {
//		this.allReceiptList = allReceiptList;
//	}

}
