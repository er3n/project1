package org.abacus.chat;

import org.primefaces.push.EventBus;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
@PushEndpoint("/{room}/{user}")
@Singleton
public class ChatResource {
 
    private final Logger logger = LoggerFactory.getLogger(ChatResource.class);
 
    @PathParam("room")
    private String room;
 
    @PathParam("user")
    private String username;
 
//    @Inject
//    private ServletContext ctx;
 
    @OnOpen
    public void onOpen(RemoteEndpoint r, EventBus eventBus) {
        logger.info("OnOpen {}", r);
        eventBus.publish(room + "/*", new Message(username, room+" # "+"onOpen"));
    }
 
    @OnClose
    public void onClose(RemoteEndpoint r, EventBus eventBus) {
        logger.info("OnClose {}", r);
        eventBus.publish(room + "/*", new Message(username, room+" # "+"onClose"));
    }
 
    @OnMessage(decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
    public Message onMessage(Message message) {
        return message;
    }
 
}