package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.control;

import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.control.util.JsfUtil;
import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.entity.Message;
import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.service.MessagingService;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Paco Saucedo
 */
@ManagedBean
@ViewScoped
public class MessagingController implements Serializable {

    private static final int DEFAULT_NUM_MESSAGES = 5;
    
    @Inject
    private MessagingService messagingService;

    private List<Message> lastSentMessages, lastReceivedMessages;
    private int numLastSentMessages, numLastReceivedMessages, numMessagesToSend;

    @PostConstruct
    protected void init() {
        numLastSentMessages = DEFAULT_NUM_MESSAGES;
        numLastReceivedMessages = DEFAULT_NUM_MESSAGES;
        numMessagesToSend = DEFAULT_NUM_MESSAGES;
        lastSentMessages = messagingService.getLastSentMessages(numLastSentMessages);
        lastReceivedMessages = messagingService.getLastReceivedMessages(numLastReceivedMessages);
    }

    public List<Message> getLastSentMessages() {
        return lastSentMessages;
    }

    public List<Message> getLastReceivedMessages() {
        return lastReceivedMessages;
    }

    public int getNumLastSentMessages() {
        return numLastSentMessages;
    }

    public void setNumLastSentMessages(int numLastSentMessages) {
        this.numLastSentMessages = numLastSentMessages;
    }

    public int getNumLastReceivedMessages() {
        return numLastReceivedMessages;
    }

    public void setNumLastReceivedMessages(int numLastReceivedMessages) {
        this.numLastReceivedMessages = numLastReceivedMessages;
    }    

    public int getNumMessagesToSend() {
        return numMessagesToSend;
    }

    public void setNumMessagesToSend(int numMessagesToSend) {
        this.numMessagesToSend = numMessagesToSend;
    }    

    public void sendSomeTestMessages() {
        try {
            messagingService.sendMessages(numMessagesToSend);

            //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/hornetqactivemqbridge/jboss/test/webapp/resource/Labels").getString("messagesSent"));
        } catch (Throwable t) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/hornetqactivemqbridge/jboss/test/webapp/resource/Labels").getString("errorSendingMessages"));
        }
    }

    public void refreshSentMessages() {
        try {
            lastSentMessages = messagingService.getLastSentMessages(numLastSentMessages);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/hornetqactivemqbridge/jboss/test/webapp/resource/Labels").getString("sentMessagesRefreshed"));
        } catch (Throwable t) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/hornetqactivemqbridge/jboss/test/webapp/resource/Labels").getString("errorReadingSentMessages"));
        }
    }

    public void refreshReceivedMessages() {
        try {
            lastReceivedMessages = messagingService.getLastReceivedMessages(numLastReceivedMessages);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/hornetqactivemqbridge/jboss/test/webapp/resource/Labels").getString("receivedMessagesRefreshed"));
        } catch (Throwable t) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/hornetqactivemqbridge/jboss/test/webapp/resource/Labels").getString("errorReadingReceivedMessages"));
        }
    }
}
