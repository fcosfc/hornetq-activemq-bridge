package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.service;

import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.entity.Message;
import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.event.MessageReceived;
import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.facade.PersistenceFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBContext;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Paco Saucedo
 */
@MessageDriven(name = "java:/hornetq/Test/In",
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType", 
                    propertyValue = "javax.jms.Queue"),            
            @ActivationConfigProperty(propertyName = "destination", 
                    propertyValue = "hornetq/Test/In")
        })
public class IncomingMessagesService implements MessageListener {

    @Inject
    private Logger logger;

    @Resource
    private EJBContext context;
            
    @Inject
    private PersistenceFacade persistenceFacade;
    
    @Inject
    private Event<MessageReceived> messageReceived;

    public IncomingMessagesService() {
    }

    @Override
    public void onMessage(javax.jms.Message message) {
        try {
            if (message instanceof TextMessage) {
                Message msg = new Message(((TextMessage) message).getText(), true);                
                persistenceFacade.save(msg);
                messageReceived.fire(new MessageReceived(msg.getContent()));
            } else {
                logger.log(Level.WARNING, "Type of message not supported: {0}", message);
            }
        } catch (Throwable t) {
            logger.log(Level.SEVERE, null, t);
            
            context.setRollbackOnly();
        }

    }

}
