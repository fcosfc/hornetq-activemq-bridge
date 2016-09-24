package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.event;

import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;

/**
 *
 * @author Paco Saucedo
 */
@Singleton
@PushEndpoint("/messagereceived")
public class MessageReceivedEndpoint extends MessagingEndpoint {
    
}
