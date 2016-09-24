package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.event;

/**
 *
 * @author Paco Saucedo
 */
public class MessagesSent {
        
    private int numMessages;

    public MessagesSent() {
    }
    
    public MessagesSent(int numMessages) {
        this.numMessages = numMessages;
    }

    public int getNumMessages() {
        return numMessages;
    }

    public void setNumMessages(int numMessages) {
        this.numMessages = numMessages;
    }
        
}
