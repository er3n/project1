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

	public void addInfo(String message,String... params) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgi", label(message)));
	}

	public void addWarn(String message,String... params) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Dikkat",
								label(message)));
	}

	public void addError(String message,String... params) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", label(message)));
	}

	public void addFatal(String message,String... params) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Olumcul Hata",
						label(message)));
	}

	public void addTest(String message) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Test",
						message));
	}

	public String label(String key,String... params) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "lbl");
		String message = bundle.getString(key);
		
		if(params != null && params.length > 0){
			MessageFormat.format(message, params);
		}
		
		return message;
	}

	public void addError(AbcBusinessException e) {
		this.addError(e.getMessage(),e.getParameters());
	}

}
