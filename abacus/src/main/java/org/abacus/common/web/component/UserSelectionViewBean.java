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

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.core.handler.UserEventHandler;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.holder.SearchUserCriteria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UserSelectionViewBean implements Serializable {

	@ManagedProperty(value = "#{userEventHandler}")
	private UserEventHandler secUserHandler;

	private Map<String, List<SecUserEntity>> resultMap = new HashMap<>();

	@PostConstruct
	public void init() {
	}

	public List<SecUserEntity> getUserList(OrganizationEntity org) {
		if (org == null) {
			return new ArrayList<SecUserEntity>();
		}
		String key = org.getId();
		if (resultMap.containsKey(key)) {
			return resultMap.get(key);
		} else {
			SearchUserCriteria cri = new SearchUserCriteria();
			cri.setHierarchy(EnumList.Hierachy.PARENT);
			cri.setOrganization(org);
			List<SecUserEntity> userList = secUserHandler.findUser(cri);
			resultMap.put(key, userList);
			return userList;
		}
	}

	public UserEventHandler getSecUserHandler() {
		return secUserHandler;
	}

	public void setSecUserHandler(UserEventHandler secUserHandler) {
		this.secUserHandler = secUserHandler;
	}

	public Map<String, List<SecUserEntity>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, List<SecUserEntity>> resultMap) {
		this.resultMap = resultMap;
	}

	// public void selectUserFromDialog(SecUserEntity user) {
	// RequestContext.getCurrentInstance().closeDialog(user);
	// }

}