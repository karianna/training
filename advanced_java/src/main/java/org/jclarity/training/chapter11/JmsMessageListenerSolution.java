package org.jclarity.training.chapter11;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageListenerSolution implements MessageListener { 

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsMessageListenerSolution.class);

    @Autowired
    private AtomicInteger counter = null;

    @Override
    public void onMessage(Message message) {
        try {
            String messageId = message.getJMSMessageID();
            
            if (message instanceof MapMessage) {
                MapMessage mm = (MapMessage)message;
                String content = mm.getString(JmsMapMessageProducerSolution.CONTENT);
                
                LOGGER.info("Processed message #: '{}'. value={}", content, messageId);
                
                counter.incrementAndGet();
            }
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}