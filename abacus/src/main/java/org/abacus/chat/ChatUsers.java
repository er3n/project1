package org.abacus.chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
@ApplicationScoped
public class ChatUsers implements Serializable {
     
    private List<String> userList;
     
    @PostConstruct
    public void init() {
    	userList = new ArrayList<String>();
    }
 
    public List<String> getUserList() {
        return userList;
    }
     
    public void removeUser(String user) {
    	if (userList.contains(user)){
    		this.userList.remove(user);
    	}
    }
     
    public void addUser(String user) {
    	if (!userList.contains(user)){
            this.userList.add(user);
    	}
    }
         
    public boolean containUser(String user) {
        return this.userList.contains(user);
    }
}