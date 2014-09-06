package org.abacus.common.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.security.SecUser;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
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
	private String messageTxt;

	@PostConstruct
	public void init() {
		findActiveSessionList();
	}

	public SecUser currentUser(){
		return sessionInfoHelper.currentUser();
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

	public List<String> getActiveUserList(){
		findActiveSessionList();
		List<String> userList = new ArrayList<String>();
		for (SessionInformation sessionInformation : allSessionList) {
			userList.add(((SecUser)sessionInformation.getPrincipal()).getUsername());
		}
		return userList;
	}


	public void pushGlobalMessage() {
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish("/message", sessionInfoHelper.currentUserName()+":"+messageTxt);
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

	public String getMessageTxt() {
		return messageTxt;
	}

	public void setMessageTxt(String messageTxt) {
		this.messageTxt = messageTxt;
	}


}
