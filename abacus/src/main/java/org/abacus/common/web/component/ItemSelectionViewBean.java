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
import org.abacus.definition.shared.entity.DefValueEntity;
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

	private List<String> valueTypeList = new ArrayList<String>();
	private Map<String, ItemDataModel> resultMap = new HashMap<>();

	public ItemDataModel getItemDataModel(EnumList.DefTypeEnum itemType, EnumList.DefItemClassEnum itemClass) {
		String key = itemType.getName()+":"+((itemType==null)?"*":itemType.name());
		if (resultMap.containsKey(key)) {
			return resultMap.get(key);
		} else {
			ItemDataModel itemDataModel =  new ItemDataModel(new ItemSearchCriteria(sessionInfoHelper.currentOrganization(), itemType, itemClass));
			resultMap.put(key, itemDataModel);
			for (EnumList.DefTypeEnum typ : EnumList.DefTypeEnum.values()) {
				if (typ.name().startsWith(itemType.name())){
					if (typ.name().length()>3){
						valueTypeList.add(typ.getDescription());
					}
				}
			}
			return itemDataModel;
		}
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

	public List<String> getValueTypeList() {
		return valueTypeList;
	}

	public void setValueTypeList(List<String> valueTypeList) {
		this.valueTypeList = valueTypeList;
	}

}