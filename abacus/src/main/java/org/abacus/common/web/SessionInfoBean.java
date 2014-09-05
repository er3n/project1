package org.abacus.common.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.security.SecUser;
import org.springframework.security.core.session.SessionInformation;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SessionInfoBean implements Serializable {

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;
	
	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	private List<SessionInformation> allSessionList;

	@PostConstruct
	public void init() {
		findActiveSessionList();
	}
	
	public void findActiveSessionList(){
		allSessionList = null;
		allSessionList = sessionInfoHelper.getActiveSessionList();
	}

	public void killSession(SessionInformation sess){
		sessionInfoHelper.killSession(sess);
		jsfMessageHelper.addInfo("deleteSuccessful", sess.getPrincipal().toString());
		findActiveSessionList();
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public List<SessionInformation> getAllSessionList() {
		return allSessionList;
	}

	public void setAllSessionList(List<SessionInformation> allSessionList) {
		this.allSessionList = allSessionList;
	}


}
