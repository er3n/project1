package org.abacus.definition.web.component;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefItemHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.definition.web.model.ItemDataModel;

@ManagedBean
@ViewScoped
public class DefItemDialogManagedBean implements Serializable {

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{defItemHandler}")
	private DefItemHandler itemHandler;

	public ItemDataModel items(EnumList.DefTypeEnum type, EnumList.DefItemClassEnum clazz) {
		String currentOrganization = sessionInfoHelper.currentRootOrganizationId();
		return new ItemDataModel(new ItemSearchCriteria(currentOrganization, type, clazz));
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public DefItemHandler getItemHandler() {
		return itemHandler;
	}

	public void setItemHandler(DefItemHandler itemHandler) {
		this.itemHandler = itemHandler;
	}

}
