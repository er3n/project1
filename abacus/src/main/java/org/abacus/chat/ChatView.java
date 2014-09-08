package org.abacus.chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.SessionInfoBean;
import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@ManagedBean
@ViewScoped
public class ChatView implements Serializable {

	private static final long serialVersionUID = 643622951952137607L;

	private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

	@ManagedProperty(value = "#{sessionInfoBean}")
	private SessionInfoBean sessionInfoBean;

	@ManagedProperty("#{chatUsers}")
	private ChatUsers chatUsers;

	private String privateMessage;

	private String username;

	private boolean loggedIn;

	private String privateUser;

	private final static String CHANNEL = "/{room}/";

	@PostConstruct
	public void init() {
		login();
	}

	public void login() {
		this.username = sessionInfoBean.currentUser().getUsername();
		loggedIn = true;
		chatUsers.addUser(this.username);	
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('subscriber').connect('/"+username+"')");
	}

	public void disconnect() {
		loggedIn = false;
		chatUsers.removeUser(this.username);	
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('subscriber').disconnect('/"+username+"')");
	}

	public List<String> getUsers() {
		List<String> listAll = sessionInfoBean.getActiveChatList();
		List<String> listChat = new ArrayList<String>();
		listAll.remove(username);
		for (String usr : listAll) {
			if (chatUsers.containUser(usr)){
				listChat.add(usr);
			}
		}
		return listChat;
	}

	public String getPrivateUser() {
		return privateUser;
	}

	public void setPrivateUser(String privateUser) {
		this.privateUser = privateUser;
	}

	public String getPrivateMessage() {
		return privateMessage;
	}

	public void setPrivateMessage(String privateMessage) {
		this.privateMessage = privateMessage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void sendPrivate() {
		eventBus.publish(CHANNEL + privateUser, username + " : "
				+ privateMessage);
		privateMessage = null;
		privateUser = null;
	}

	public SessionInfoBean getSessionInfoBean() {
		return sessionInfoBean;
	}

	public void setSessionInfoBean(SessionInfoBean sessionInfoBean) {
		this.sessionInfoBean = sessionInfoBean;
	}

	public ChatUsers getChatUsers() {
		return chatUsers;
	}

	public void setChatUsers(ChatUsers chatUsers) {
		this.chatUsers = chatUsers;
	}
}