package org.abacus.chat;

import org.primefaces.push.Decoder;

/**
 * A Simple {@link org.primefaces.push.Decoder} that decode a String into a {@link Message} object.
 */
public class MessageDecoder implements Decoder<String,Message> {
 
    //@Override
    public Message decode(String s) {
    	String[] userAndMessage = s.split(":");
        return new Message(userAndMessage[0], userAndMessage[1]);
    }
}