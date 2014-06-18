package org.abacus.common.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class JsfDialogHelper implements Serializable {

	private Map<String, Object> defaultDialogOptions;

	@PostConstruct
	public void init() {
		defaultDialogOptions = new HashMap<>();
		defaultDialogOptions.put("modal", true);
		defaultDialogOptions.put("draggable", true);
		defaultDialogOptions.put("resizable", true);
		defaultDialogOptions.put("contentHeight", 600);
		defaultDialogOptions.put("contentWidth", 1050);
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

	public void openTestDocDialog() {
		Map<String, String> paramsHashMap = new HashMap<>();
		this.openDialog("/app/test/testDocDialog", paramsHashMap);
	}
	
}
