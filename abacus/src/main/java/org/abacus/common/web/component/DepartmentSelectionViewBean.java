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

import org.abacus.organization.core.handler.DepartmentHandler;
import org.abacus.organization.shared.entity.DepartmentEntity;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DepartmentSelectionViewBean implements Serializable {

	@ManagedProperty(value = "#{departmentHandler}")
	private DepartmentHandler departmentHandler;
	
	private Map<String, List<DepartmentEntity>> resultMap = new HashMap<>();
	
	@PostConstruct
	public void init() {
	}

	public List<DepartmentEntity> getDepartmentList(String username) {
		if (username==null){
			return new ArrayList<DepartmentEntity>();
		}
		if (resultMap.containsKey(username)) {
			return resultMap.get(username);
		} else {
			List<DepartmentEntity> depList = departmentHandler.findUserDepartmentList(username);
			resultMap.put(username, depList);
			return depList;
		}
	}

	public DepartmentHandler getDepartmentHandler() {
		return departmentHandler;
	}

	public void setDepartmentHandler(DepartmentHandler departmentHandler) {
		this.departmentHandler = departmentHandler;
	}

//	public void selectUserFromDialog(SecUserEntity user) {
//		RequestContext.getCurrentInstance().closeDialog(user);
//	}


}