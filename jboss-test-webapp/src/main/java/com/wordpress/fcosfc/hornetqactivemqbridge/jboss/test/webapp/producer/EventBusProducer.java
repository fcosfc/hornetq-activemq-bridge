package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.producer;

import javax.enterprise.inject.Produces;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Paco Saucedo
 */
public class EventBusProducer {
           
    @Produces
    public EventBus getEventBus() {
        return EventBusFactory.getDefault().eventBus();
    }
    
}
