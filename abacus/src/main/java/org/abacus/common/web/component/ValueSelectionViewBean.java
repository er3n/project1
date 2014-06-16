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

	private Map<String, List<DefValueEntity>> resultMap = new HashMap<>();

	public void init() {
	}

	public List<DefValueEntity> getValueList(EnumList.DefTypeEnum typeEnum) {
		if (typeEnum==null){
			return new ArrayList<DefValueEntity>();
		}
		if (resultMap.containsKey(typeEnum.name())) {
			return resultMap.get(typeEnum.name());
		} else {
			List<DefValueEntity> list = defValueHandler.getValueList(sessionInfoHelper.currentRootOrganizationId(), typeEnum);
			resultMap.put(typeEnum.name(), list);
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

}