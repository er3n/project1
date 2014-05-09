package org.abacus.definition.web;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;






import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefItemHandler;
import org.abacus.definition.shared.event.ReadItemEvent;
import org.abacus.definition.shared.event.RequestReadItemEvent;


public class ItemViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;
	
	@ManagedProperty(value = "#{itemHandler}")
	private DefItemHandler itemHandler;
	
	public void init(){
		ReadItemEvent readItemEvent = itemHandler.findItem(new RequestReadItemEvent());
	}

}
