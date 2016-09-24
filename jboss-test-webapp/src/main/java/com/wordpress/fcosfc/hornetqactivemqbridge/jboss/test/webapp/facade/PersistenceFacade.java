package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.facade;

import com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.entity.Message;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Paco Saucedo
 */
@Stateless
public class PersistenceFacade {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(Message message) {
        em.persist(message);
    }
    
    public List<Message> getLastReceivedMessages(int numMessages) {
        return em.createNamedQuery("Message.Received")
                .setMaxResults(numMessages)
                .getResultList();                
    }
    
    public List<Message> getLastSentMessages(int numMessages) {
        return em.createNamedQuery("Message.Sent")
                .setMaxResults(numMessages)
                .getResultList();   
    }
}
