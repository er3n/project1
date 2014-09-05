package org.abacus.common.web;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONDecoder;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/message")
public class MessageResource {
 
	@PathParam("user")
	private String username;
	 
    @OnMessage(decoders={JSONDecoder.class}, encoders = {JSONEncoder.class})
    public String onMessage(String message) {
        return message;
    }
}
