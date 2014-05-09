package org.abacus.common.web;

import java.io.Serializable;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.abacus.common.security.SecUser;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class SessionInfoHelper implements Serializable {

	public SecUser currentUser(){
		SecUser secUser = (SecUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return secUser;
	} 
	
	public String currentUserName(){
		return currentUser().getUsername();
	} 
	
	public boolean isAuthenticated(){
		Authentication authentucation = SecurityContextHolder.getContext().getAuthentication();
		if(authentucation != null && authentucation.getPrincipal() instanceof SecUser){
			return true;
		}
		
		return false;
	}

	public String currentOrganizationId() {
		return currentUser().getSelectedOrganization().getId();
	}
	
	public void redirect(){
		FacesContext context = FacesContext.getCurrentInstance();
	    NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
	    navigationHandler.handleNavigation(context, null, "/app/index.abc?faces-redirect=true");
	}
	
	public OrganizationEntity currentRootOrganization(){
		return currentUser().getRootOrganization();
	}

	public String currentRootOrganizationId() {
		return currentRootOrganization().getId();
	}
	
}
