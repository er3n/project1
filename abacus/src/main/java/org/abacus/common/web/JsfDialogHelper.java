package org.abacus.common.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.EnumList.DefItemClassEnum;
import org.abacus.definition.shared.constant.EnumList.DefTypeEnum;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class JsfDialogHelper implements Serializable {

	private Map<String, Object> defaultDialogOptions;

	@PostConstruct
	public void init() {
		defaultDialogOptions = new HashMap<>();
		defaultDialogOptions.put("modal", true);
		defaultDialogOptions.put("draggable", false);
		defaultDialogOptions.put("resizable", false);
		defaultDialogOptions.put("contentHeight", 320);
	}

	public void openDefValueDialog(EnumList.DefTypeEnum type) {
		Map<String, String> paramsHashMap = new HashMap<>();
		paramsHashMap.put("type", type.name());
		this.openDialog("defValueDialog", paramsHashMap);
	}
	
	//Component Oldu
	public void openItemDialog(DefTypeEnum type, DefItemClassEnum clazz) {
		Map<String, String> paramsHashMap = new HashMap<>();
		paramsHashMap.put("type", type.name());
		paramsHashMap.put("clazz", clazz.name());
		this.openDialog("defItemDialog", paramsHashMap);
	}

	public void openDialog(String dialog, Map<String, String> paramMap) {

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		if (paramMap != null) {
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				List<String> param = new ArrayList<>();
				String value = paramMap.get(key);
				param.add(value);
				params.put(key, param);
			}
		}

		RequestContext.getCurrentInstance().openDialog(dialog, defaultDialogOptions, params);
	}

}
