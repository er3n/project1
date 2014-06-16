package org.abacus.user.web;

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
import org.abacus.user.core.handler.SecUserHandler;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.holder.SearchUserCriteria;
import org.primefaces.context.RequestContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UserTableViewBean implements Serializable {

	@ManagedProperty(value = "#{secUserHandler}")
	private SecUserHandler secUserHandler;
	private Map<String, List<SecUserEntity>> resultMap = new HashMap<>();
	
	@PostConstruct
	public void init() {
	}

	public List<SecUserEntity> getUserList(OrganizationEntity org) {
		if (org==null){
			return new ArrayList<SecUserEntity>();
		}
		if (resultMap.containsKey(org.getId())) {
			return resultMap.get(org.getId());
		} else {
			SearchUserCriteria cri = new SearchUserCriteria();
			cri.setHierarchy(EnumList.Hierachy.PARENT);
			cri.setOrganization(org);
			List<SecUserEntity> userList = secUserHandler.findUser(cri);
			resultMap.put(org.getId(), userList);
			return userList;
		}
	}

	public void selectUserFromDialog(SecUserEntity user) {
		RequestContext.getCurrentInstance().closeDialog(user);
	}

	public SecUserHandler getSecUserHandler() {
		return secUserHandler;
	}

	public void setSecUserHandler(SecUserHandler secUserHandler) {
		this.secUserHandler = secUserHandler;
	}

}