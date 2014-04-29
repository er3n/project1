package org.abacus.common.web;

import org.abacus.common.security.SecUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SessionInfoHelper {


	public SecUser currentUser(){
		SecUser secUser = (SecUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return secUser;
	} 
	
	public String currentUserName(){
		return currentUser().getUsername();
	} 
	
	public boolean isAuthenticated(){
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SecUser){
			return true;
		}
		
		return false;
	}
	
}
