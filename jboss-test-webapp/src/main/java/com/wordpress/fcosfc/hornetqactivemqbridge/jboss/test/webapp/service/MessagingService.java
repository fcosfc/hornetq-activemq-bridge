package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.service;

import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.entity.Message;
import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.event.MessagesSent;
import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.facade.MessagingFacade;
import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.facade.PersistenceFacade;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;

/**
 *
 * @author Paco Saucedo
 */
@Stateless
public class MessagingService {

    @Inject
    private PersistenceFacade persistenceFacade;

    @Inject
    private MessagingFacade messagingFacade;

    @Inject
    private Event<MessagesSent> messagesSent;

    @Asynchronous
    public void sendMessages(int numMessages) {
        Message message;
        Calendar calendar = Calendar.getInstance();

        try {
            for (int i = 0; i < numMessages; i++) {
                message = new Message("Message from HornetQ " + calendar.getTimeInMillis(), false);
                messagingFacade.sendMessage(message.getContent());
                persistenceFacade.save(message);
            }
            messagesSent.fire(new MessagesSent(numMessages));
        } catch (JMSException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Message> getLastReceivedMessages(int numMessages) {
        return persistenceFacade.getLastReceivedMessages(numMessages);
    }

    public List<Message> getLastSentMessages(int numMessages) {
        return persistenceFacade.getLastSentMessages(numMessages);
    }

}
