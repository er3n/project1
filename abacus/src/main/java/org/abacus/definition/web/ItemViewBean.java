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
import org.abacus.definition.shared.event.ItemCreatedEvent;
import org.abacus.definition.shared.event.ItemProductCreatedEvent;
import org.abacus.definition.shared.event.ItemUpdatedEvent;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.event.UpdateItemEvent;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.definition.web.model.ItemDataModel;
import org.abacus.definition.web.shared.event.ItemProductUptatedEvent;
import org.abacus.definition.web.shared.event.UpdateItemProductEvent;
import org.abacus.organization.shared.entity.OrganizationEntity;
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

	@ManagedProperty(value = "#{defUnitHandler}")
	private DefUnitHandler defUnitHandler;

	private ItemDataModel itemLazyModel;

	private DefItemEntity selectedItem;

	private List<DefUnitGroupEntity> allUnitGroupList;

	private EnumList.DefTypeEnum type;

	private EnumList.DefItemClassEnum clazz;

	private Set<DefUnitCodeEntity> selectedUnitGroupsUnitCodeSet;

	private Set<DefUnitCodeEntity> selectedUnitGroupsSelectedUnitCodeSet;

	private DefItemProductEntity selectedItemProduct;

	private Boolean displayProductInfo;

	@PostConstruct
	public void init() {
		this.initParameters();
		itemLazyModel = new ItemDataModel(itemSearchCriteria);
		this.initUnitGroups();
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
			String organization = sessionInfoHelper.currentOrganizationId();
			ItemUpdatedEvent updatedEvent = itemHandler.updateItem(new UpdateItemEvent(selectedItem, selectedUnitGroupsSelectedUnitCodeSet, userUpdated, organization));
			this.itemSelected();
			jsfMessageHelper.addInfo("updateSuccesssful");
		} catch (ItemAlreadyExistsException e) {
			jsfMessageHelper.addError("itemExistsWithThisTypeAndCode");
		}
	}

	public void unitGroupSelected() {
		if (selectedItem.getUnitGroup() == null) {
			selectedUnitGroupsUnitCodeSet = null;
			return;
		}
		List<DefUnitCodeEntity> selectedUnitGroupsUnitCodeList = defUnitHandler.getUnitCodeList(selectedItem.getUnitGroup().getId());
		selectedUnitGroupsUnitCodeSet = new LinkedHashSet<>(selectedUnitGroupsUnitCodeList);
	}

	public void newItem() {
		try {
			String createdUser = sessionInfoHelper.currentUserName();
			ItemCreatedEvent createdEvent = itemHandler.newItem(new CreateItemEvent(selectedItem, selectedUnitGroupsSelectedUnitCodeSet, createdUser));
			selectedItem = null;
			jsfMessageHelper.addInfo("craeteSuccessful");
		} catch (ItemAlreadyExistsException e) {
			jsfMessageHelper.addError("itemExistsWithThisTypeAndCode");
		}
	}

	public void newItemProduct() {
		String createdUser = sessionInfoHelper.currentUserName();
		ItemProductCreatedEvent createdEvent = itemHandler.newItemProduct(new CreateItemProductEvent(selectedItemProduct,createdUser));
		selectedItemProduct = null;
		this.itemSelected();
		jsfMessageHelper.addInfo("craeteSuccessful");
	}

	public void updateItemProduct() {
		String userUpdated = sessionInfoHelper.currentUserName();
		ItemProductUptatedEvent updatedEvent = itemHandler.updateItemProduct(new UpdateItemProductEvent(selectedItemProduct,userUpdated));
		selectedItemProduct = null;
		
		this.itemSelected();
		
		jsfMessageHelper.addInfo("updateSuccesssful");
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
		OrganizationEntity organization = sessionInfoHelper.currentRootOrganization();
		selectedItem = new DefItemEntity();
		selectedItem.setOrganization(organization);
		selectedItem.setType(new DefTypeEntity(type.name()));
		selectedItem.setItemClass(clazz);
		selectedItem.setCategory(new DefValueEntity());

		this.itemChange();

		this.unitGroupSelected();
	}

	public void newItemProductSelected() {
		selectedItemProduct = new DefItemProductEntity();
		selectedItemProduct.setItem(selectedItem);
		selectedItemProduct.setMaterialItem(new DefItemEntity());
	}

	private void itemChange() {
		selectedItemProduct = null;
	}

	private void initParameters() {
		String currentOrganization = sessionInfoHelper.currentRootOrganizationId();
		String itemTypeStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
		String itemClassStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("class");
		type = EnumList.DefTypeEnum.valueOf(itemTypeStr);
		clazz = EnumList.DefItemClassEnum.valueOf(itemClassStr);
		itemSearchCriteria = new ItemSearchCriteria(currentOrganization, type, clazz);

		displayProductInfo = EnumList.DefTypeEnum.ITM_SR_ST.equals(type) && EnumList.DefItemClassEnum.STK_P.equals(clazz);
	}

	public void chooseCategory() {
		jsfDialogHelper.openDefValueDialog(EnumList.DefTypeEnum.VAL_CATEGORY);
	}

	public void chooseItem() {
		jsfDialogHelper.openItemDialog(EnumList.DefTypeEnum.ITM_SR_ST, EnumList.DefItemClassEnum.STK_M);
	}

	public void onMaterialChosen(SelectEvent event) {
		DefItemEntity item = (DefItemEntity) event.getObject();
		selectedItemProduct.setMaterialItem(item);
	}

	public void clearMaterial() {
		selectedItemProduct.setMaterialItem(new DefItemEntity());
	}

	private void initUnitGroups() {
		String rootOrganizationId = sessionInfoHelper.currentRootOrganizationId();
		this.allUnitGroupList = defUnitHandler.getUnitGroupList(rootOrganizationId);
	}

	public void onCategoryChosen(SelectEvent event) {
		DefValueEntity category = (DefValueEntity) event.getObject();
		selectedItem.setCategory(category);
	}

	public void clearCategory() {
		selectedItem.setCategory(new DefValueEntity());
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

}
