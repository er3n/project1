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
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.handler.DepartmentHandler;
import org.abacus.organization.shared.entity.DepartmentEntity;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DepartmentSelectionViewBean implements Serializable {

	@ManagedProperty(value = "#{departmentHandler}")
	private DepartmentHandler departmentHandler;
	
	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;
		
	private Map<String, List<DepartmentEntity>> resultMap = new HashMap<>();
	
	@PostConstruct
	public void init() {
	}

	public List<DepartmentEntity> getDepartmentList(String username, EnumList.OrgDepartmentGroupEnum depGroup, Boolean isOnlyOrg) {
		if (username==null){
			return new ArrayList<DepartmentEntity>();
		}
		Boolean isOnlyOrganization = (isOnlyOrg==null)?Boolean.TRUE:isOnlyOrg;//Default=TRUE
		String key = username+((depGroup==null)?"":"-"+depGroup.name()+"-"+isOnlyOrganization.toString());
		if (resultMap.containsKey(key)) {
			return resultMap.get(key);
		} else {
			List<DepartmentEntity> depList = null;
			if (isOnlyOrganization){
				depList = departmentHandler.findUserDepartmentListOrgOnly(username, depGroup, sessionInfoHelper.currentOrganization());
			} else {
				depList = departmentHandler.findUserDepartmentListOrgLike(username, depGroup, sessionInfoHelper.currentOrganization().getRootOrganization());
			}
			resultMap.put(key, depList);
			return depList;
		}
	}

	public DepartmentHandler getDepartmentHandler() {
		return departmentHandler;
	}

	public void setDepartmentHandler(DepartmentHandler departmentHandler) {
		this.departmentHandler = departmentHandler;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

//	public void selectUserFromDialog(SecUserEntity user) {
//		RequestContext.getCurrentInstance().closeDialog(user);
//	}


}