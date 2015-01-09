package org.abacus.common.web;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.abacus.common.shared.AbcBusinessException;
import org.springframework.stereotype.Component;

@Component
public class JsfMessageHelper implements Serializable {

	public void addError(AbcBusinessException e) {
		this.addError(e.getName(), e.getParams());
	}

	public void addError(String message, String... params) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", label(message, params)));
	}

	public void addInfo(String message, String... params) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgi", label(message, params)));
	}

	public void addWarn(String message, String... params) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Dikkat", label(message, params)));
	}

	public void addFatal(String message, String... params) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", label(message, params)));
	}

	public String label(String key, String... params) {
		StringBuffer message= new StringBuffer();
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "lbl");
			message.append(bundle.getString(key)+"\n");
		} catch (Exception e) {
			message.append(key+"\n");
		}
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++){
				message.append(params[i]+"\n");
		    }
		}
		return message.toString();
	}

}
