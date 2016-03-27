package org.abacus.common.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.security.SecUser;
import org.abacus.organization.shared.entity.OrganizationEntity;
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
	}

	public SecUser currentUser(){
		return sessionInfoHelper.currentUser();
	}

	public OrganizationEntity currentOrganization(){
		return sessionInfoHelper.currentOrganization();
	}
	
	public Boolean isRootUser(){
		return sessionInfoHelper.isRootUser();
	}

	public void findActiveSessionList(){
		allSessionList = sessionInfoHelper.getActiveSessionList(false);
	}

	public void killSession(SessionInformation sess){
		sessionInfoHelper.killSession(sess);
		jsfMessageHelper.addInfo("deleteSuccessful", sess.getPrincipal().toString());
		findActiveSessionList();
	}

	public void showMessage(){
//		FacesContext context = FacesContext.getCurrentInstance();
//	    Map map = context.getExternalContext().getRequestParameterMap();
//	    String msg = (String) map.get("msg");
//	    jsfMessageHelper.addSimple("Mesaj", (msg==null?"?":msg));
	    jsfMessageHelper.addInfo("Mesaj:", (messageTxt==null?"?":messageTxt));
	}
	/*
	public void pushGlobalMessage() {
		if (messageTxt==null || messageTxt.equals("")){
			return;
		}
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish("/message", sessionInfoHelper.currentUserName()+":"+messageTxt);
	}
	*/
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
