package org.abacus.common.web;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.abacus.common.shared.AbcBusinessException;
import org.springframework.stereotype.Component;

@Component
public class JsfMessageHelper implements Serializable {

	public void addException(AbcBusinessException e) {
		StringBuffer message= new StringBuffer();
		if (e.getParams() != null && e.getParams().length > 0) {
			for (int i = 0; i < e.getParams().length; i++){
				message.append("# "+e.getParams()[i]+" ");
		    }
		}
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getName(), message.toString()));
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
		String message = null;
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "lbl");
			message = bundle.getString(key);
		} catch (Exception e) {
			message = key;
		}
		message = MessageFormat.format(message, params);
		return message;
	}	

}
