package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.event;

/**
 *
 * @author Paco Saucedo
 */
public class MessageReceived {
        
    private String content;

    public MessageReceived() {
    }
    
    public MessageReceived(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
        
}
