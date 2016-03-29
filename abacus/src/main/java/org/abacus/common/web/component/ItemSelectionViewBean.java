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
import org.abacus.definition.shared.entity.DefItemEntity;
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
	private Map<String, List<String>> typeFilterMap = new HashMap<>();

	public ItemDataModel getItemDataModel(EnumList.DefTypeEnum itemType, EnumList.DefItemClassEnum itemClass) {
		if (itemType==null){
			return new ItemDataModel(new ItemSearchCriteria(null,null,null));
		}		
		String key = itemType.getName()+((itemClass==null)?"":"-"+itemClass.name());
		if (resultMap.containsKey(key)) {
			return resultMap.get(key);
		} else {
			ItemSearchCriteria crit = new ItemSearchCriteria(sessionInfoHelper.currentOrganization().getRootOrganization(), itemType, itemClass);
			if (itemType.equals(EnumList.DefTypeEnum.ITM_CM_PE)){
				String userId = sessionInfoHelper.currentUser().getUsername();
				List<DefItemEntity> loginItemList = defItemHandler.loginItemList(userId, EnumList.DefTypeEnum.ITM_CM_PE.getName(), sessionInfoHelper.currentOrganization().getId());
				if (loginItemList!=null && loginItemList.size()>0){
					crit.setItemList(loginItemList);
					crit.setCode(userId);						
				}
			}
			ItemDataModel itemDataModel =  new ItemDataModel(crit);
			resultMap.put(key, itemDataModel);
			return itemDataModel;
		}
	}

	public List<String> getTypeFilter(EnumList.DefTypeEnum itemType) {
		if (itemType==null){
			return new ArrayList<String>();
		}
		String key = itemType.getName();
		if (typeFilterMap.containsKey(key)) {
			return typeFilterMap.get(key);
		} else {
			List<String> valueTypeList = new ArrayList<String>();
			for (EnumList.DefTypeEnum typ : EnumList.DefTypeEnum.values()) {
				if (typ.name().startsWith(itemType.name())){
					if (typ.name().length()>3){
						valueTypeList.add(typ.getDescription());
					}
				}
			}
			typeFilterMap.put(key, valueTypeList);
			return valueTypeList;
		}
	}

	public String getTypeName(EnumList.DefTypeEnum itemType){
		if (itemType==null){
			return "???";
		}
		return itemType.getDescription();
	}
	
	@PostConstruct
	public void init() {
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

}