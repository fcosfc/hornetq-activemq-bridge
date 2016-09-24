package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.event;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.push.EventBus;

/**
 *
 * @author Paco Saucedo
 */
@ApplicationScoped
public class MessagingObserver {

    @Inject
    private EventBus eventBus;

    public void messagesSent(@Observes MessagesSent messageSent) {
        eventBus.publish("/messagessent",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        ResourceBundle.getBundle("/com/wordpress/fcosfc/hornetqactivemqbridge/jboss/test/webapp/resource/Labels").getString("messagingInterface"),
                        MessageFormat.format(
                                ResourceBundle.getBundle("/com/wordpress/fcosfc/hornetqactivemqbridge/jboss/test/webapp/resource/Labels").getString("messagesSentDetected"),
                                messageSent.getNumMessages())));        
    }

    public void messageReceived(@Observes MessageReceived messageReceived) {
        eventBus.publish("/messagereceived",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        ResourceBundle.getBundle("/com/wordpress/fcosfc/hornetqactivemqbridge/jboss/test/webapp/resource/Labels").getString("messagingInterface"),
                        MessageFormat.format(
                                ResourceBundle.getBundle("/com/wordpress/fcosfc/hornetqactivemqbridge/jboss/test/webapp/resource/Labels").getString("messageReceivedDetected"),
                                messageReceived.getContent())));
    }
}
