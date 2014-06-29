package org.jclarity.training.chapter11;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class JmsMapMessageProducerSolution {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsMapMessageProducerSolution.class);

    protected static final String MESSAGE_COUNT = "messageCount";
    protected static final String CONTENT = "content";

    @Autowired
    private JmsTemplate jmsProducerTemplate = null;
    private int messageCount = 100;

    /**
     * Generate XML JMS messages
     */
    @PostConstruct
    public void generateMessages() {
        
        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            final String messageId = "Message number is " + i + ".";
            
            final String content = "‹?xml version=\"1.0\"›\n" + 
                                   "‹Message›Message Count is:" + index + "‹/Message›";
            
            jmsProducerTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    MapMessage message = session.createMapMessage();
                    message.setJMSMessageID(messageId);
                    
                    message.setString(CONTENT, content);

                    LOGGER.info("Sending message: " + messageId);
                    return message;
                }
            });
        }
    }
}