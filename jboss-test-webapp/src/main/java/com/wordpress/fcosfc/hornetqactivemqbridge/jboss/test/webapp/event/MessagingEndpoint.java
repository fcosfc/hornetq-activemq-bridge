package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.event;

import javax.faces.application.FacesMessage;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.impl.JSONEncoder;

/**
 *
 * @author Paco Saucedo
 */
public abstract class MessagingEndpoint {
    
    @OnMessage(encoders = {JSONEncoder.class})
    public FacesMessage onMessage(FacesMessage message) {
        return message;
    }
    
}
