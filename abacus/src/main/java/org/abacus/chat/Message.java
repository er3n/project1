package org.abacus.chat;

public class Message {
    
    private String text;
    private String user;
 
    public Message(String user, String text) {
        this.text = text;
        this.user = user;
    }
     
    public String getText() {
        return text;
    }
 
    public Message setText(String text) {
        this.text = text;
        return this;
    }
 
    public String getUser() {
        return user;
    }
 
    public Message setUser(String user) {
        this.user = user;
        return this;
    }
 
}