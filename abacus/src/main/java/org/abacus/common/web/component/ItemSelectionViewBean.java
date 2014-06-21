package org.abacus.common.web.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefItemHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.definition.web.model.ItemDataModel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemSelectionViewBean implements Serializable {

	@ManagedProperty(value = "#{defItemHandler}")
	private DefItemHandler defItemHandler;
	
	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private Map<String, ItemDataModel> resultMap = new HashMap<>();
	private List<EnumList.DefTypeEnum> itemTypeList = new ArrayList<>();

	private EnumList.DefTypeEnum selectedItemType;
	private EnumList.DefItemClassEnum selectedItemClass;
	
	public ItemDataModel initItemDataModel(EnumList.DefTypeEnum itemType, EnumList.DefItemClassEnum itemClass) {
		if (this.selectedItemType==null){
			this.selectedItemType = itemType;
			this.selectedItemClass = itemClass;
			return refreshItemDataModel();
		}
		return getItemDataModel();
	}
	
	public ItemDataModel refreshItemDataModel() {
		resultMap.clear();
		return getItemDataModel();
	}

	public ItemDataModel getItemDataModel() {
		String key = selectedItemType.getName()+":"+((selectedItemClass==null)?"*":selectedItemClass.name());
		if (resultMap.containsKey(key)) {
			return resultMap.get(key);
		} else {
			ItemDataModel itemDataModel =  new ItemDataModel(new ItemSearchCriteria(sessionInfoHelper.currentOrganization(), selectedItemType, selectedItemClass));
			resultMap.put(key, itemDataModel);
			return itemDataModel;
		}
	}
	
	@PostConstruct
	public void init() {
		itemTypeList.add(EnumList.DefTypeEnum.ITM_SR_ST);//Stok
		itemTypeList.add(EnumList.DefTypeEnum.ITM_SR_FN);//Finans
//		itemTypeList.add(EnumList.DefTypeEnum.ITM_SR_FA);//Demirbas
	}

	public DefItemHandler getDefItemHandler() {
		return defItemHandler;
	}

	public void setDefItemHandler(DefItemHandler defItemHandler) {
		this.defItemHandler = defItemHandler;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public List<EnumList.DefTypeEnum> getItemTypeList() {
		return itemTypeList;
	}

	public void setItemTypeList(List<EnumList.DefTypeEnum> itemTypeList) {
		this.itemTypeList = itemTypeList;
	}

	public EnumList.DefTypeEnum getSelectedItemType() {
		return selectedItemType;
	}

	public void setSelectedItemType(EnumList.DefTypeEnum selectedItemType) {
		this.selectedItemType = selectedItemType;
	}

	public EnumList.DefItemClassEnum getSelectedItemClass() {
		return selectedItemClass;
	}

	public void setSelectedItemClass(EnumList.DefItemClassEnum selectedItemClass) {
		this.selectedItemClass = selectedItemClass;
	}


}