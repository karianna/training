package org.jclarity.training.chapter11;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests that the generated messages were all processed by the JMS listener.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml"})
public class JmsMessageListenerTest {

    final Logger logger = LoggerFactory.getLogger(JmsMessageListenerTest.class);

    @Autowired
    private AtomicInteger counter = null;

    @Test
    public void testMessage() throws Exception {
        int expectedCount = 100;
        
        // Give listener a chance to process messages
        Thread.sleep(2 * 1000);
        
        assertEquals("Message is not '" + expectedCount + "'.", expectedCount, counter.get());
    }

}
