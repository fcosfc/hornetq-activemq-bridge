package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.facade;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.XAConnection;
import javax.jms.XAConnectionFactory;
import javax.jms.XASession;

/**
 *
 * @author Paco Saucedo
 */
@Stateless
public class MessagingFacade {

    @Inject
    private Logger logger;

    @Resource(name = "java:/JmsXA")
    private XAConnectionFactory connectionFactory;

    @Resource(name = "java:/hornetq/Test/Out")
    private Queue queue;

    private XAConnection connection;

    private XASession session;

    private MessageProducer messageProducer;

    @PostConstruct
    public void init() {
        try {
            connection = connectionFactory.createXAConnection();
            session = connection.createXASession();
            messageProducer = session.createProducer(queue);
        } catch (JMSException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }

    public void sendMessage(String message) throws JMSException {
        messageProducer.send(session.createTextMessage(message));
    }

    @PreDestroy
    public void bye() {
        try {
            messageProducer.close();
        } catch (JMSException ignore) {
        }
        try {
            session.close();
        } catch (JMSException ignore) {
        }
        try {
            connection.close();
        } catch (JMSException ignore) {
        }
    }

}
