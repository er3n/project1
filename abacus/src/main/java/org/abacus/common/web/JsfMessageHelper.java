package org.abacus.common.web;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

@Component
public class JsfMessageHelper implements Serializable {

	public void addInfo(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Bilgi", label(message)));
	}

	public void addWarn(String message) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Dikkat",
								label(message)));
	}

	public void addError(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", label(message)));
	}

	public void addFatal(String message) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Olumcul Hata",
						label(message)));
	}

	public String label(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "lbl");
		String message = bundle.getString(key);
		return message;
	}

}
