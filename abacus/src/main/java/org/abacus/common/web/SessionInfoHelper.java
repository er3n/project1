package org.abacus.common.web;

import java.io.Serializable;
import java.util.Set;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.abacus.common.security.SecUser;
import org.abacus.organization.core.persistance.repository.OrganizationRepository;
import org.abacus.organization.core.util.OrganizationUtils;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class SessionInfoHelper implements Serializable {
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private OrganizationUtils organizationUtils;

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

	public void companyChanged(){
		OrganizationEntity selectedOrganization = organizationRepository.fetchOrganization(currentOrganizationId());
		Set<FiscalYearEntity> fiscalYearSet = organizationUtils.findCompanyFiscalYearSet(selectedOrganization);
		FiscalYearEntity selectedFiscalYear = organizationUtils.findDefaultFiscalYear(fiscalYearSet);
		currentUser().setCompanyFiscalYearSet(fiscalYearSet);
		currentUser().setSelectedFiscalYear(selectedFiscalYear);
		this.redirect();
	}
	
	public void fiscalYearChanged(){
		this.redirect();
	}
	
	private void redirect(){
		FacesContext context = FacesContext.getCurrentInstance();
	    NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
	    navigationHandler.handleNavigation(context, null, "/app/index.abc?faces-redirect=true");
	}
	
	public OrganizationEntity currentOrganization() {
		return currentUser().getSelectedOrganization();
	}

	public String currentOrganizationId() {
		return currentUser().getSelectedOrganization().getId();
	}
	
	public OrganizationEntity currentRootOrganization(){
		return currentUser().getRootOrganization();
	}

	public String currentRootOrganizationId() {
		return currentRootOrganization().getId();
	}

	public FiscalYearEntity selectedFiscalYear() {
		return currentUser().getSelectedFiscalYear();
	}

	public String selectedFiscalYearId() {
		return currentUser().getSelectedFiscalYear().getId();
	}
	
}
