package org.abacus.definition.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefItemHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.SelectionEnum;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;
import org.abacus.definition.shared.holder.ItemSearchCriteria;


public class ItemViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;
	
	@ManagedProperty(value = "#{itemHandler}")
	private DefItemHandler itemHandler;
	
	private ItemSearchCriteria itemSearchCriteria;
	
	private List<DefItemEntity> itemList;
	
	public void init(){
		String currentOrganization = sessionInfoHelper.currentRootOrganizationId();
		ReadItemEvent readItemEvent = itemHandler.findItem(new RequestReadItemEvent(currentOrganization));
		itemList = readItemEvent.getItemList();
		
		try{
			String itemTypeStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
			String itemClassStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
		}catch(Exception e){
			
		}
	}
	
	

}
