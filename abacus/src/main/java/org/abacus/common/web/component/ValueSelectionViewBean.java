package org.abacus.common.web.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefValueHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefValueEntity;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ValueSelectionViewBean implements Serializable {

	@ManagedProperty(value = "#{defValueHandler}")
	private DefValueHandler defValueHandler;
	
	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;
	
	private List<String> valueTypeList = new ArrayList<String>();

	private Map<String, List<DefValueEntity>> resultMap = new HashMap<>();

	public void init() {
	}

	public List<DefValueEntity> getValueList(EnumList.DefTypeEnum typeEnum) {
		if (typeEnum==null){
			return new ArrayList<DefValueEntity>();
		}
		String key = typeEnum.name();
		if (resultMap.containsKey(key)) {
			return resultMap.get(key);
		} else {
			List<DefValueEntity> list = defValueHandler.getValueList(sessionInfoHelper.currentRootOrganizationId(), typeEnum);
			resultMap.put(key, list);
			valueTypeList.clear();
			for (EnumList.DefTypeEnum typ : EnumList.DefTypeEnum.values()) {
				if (typ.name().startsWith(typeEnum.name())){
					if (typ.name().length()>3){
						valueTypeList.add(typ.getDescription());
					}
				}
			}
			return list;
		}
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

	public List<String> getValueTypeList() {
		return valueTypeList;
	}

	public void setValueTypeList(List<String> valueTypeList) {
		this.valueTypeList = valueTypeList;
	}

}