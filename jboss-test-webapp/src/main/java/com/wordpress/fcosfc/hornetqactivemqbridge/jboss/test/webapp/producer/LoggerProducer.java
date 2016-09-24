package com.wordpress.fcosfc.hornetqactivemqbridge.jboss.test.webapp.producer;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Paco Saucedo
 */
public class LoggerProducer {
    
    @Produces
    public Logger getLogger(InjectionPoint ip) {
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }
}
