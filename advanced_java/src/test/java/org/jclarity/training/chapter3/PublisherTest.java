package org.jclarity.training.chapter3;

import org.junit.Rule;
import org.junit.Test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;

/**
 * Test the publisher, using JMock to assist
 */
public class PublisherTest {
    
    /** 
     * Create a Mockery (context) in which the Publisher can exist in
     * 
     * <code>@Rule</code> Rules are used to add additional functionality 
     * which apply to all tests within a test class, but in a more generic way.
     */
    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

    /**
     * Write a test using a mock Subscriber, break up into several steps:
     * 
     * <p>Step one, set up the context</p>
     * <ol>
     *     <li>We first set up the context in which our test will execute.</li>
     *     <li>We create a Publisher to test.</li>
     *     <li>We create a mock Subscriber that should receive the message.</li> 
     *     <li>We then register the Subscriber with the Publisher.</li>
     *     <li>Finally we create a message object to publish.</li>
     * </ol>
     * <p>Step two, create expectations.</p>
     * <p>Step three, execute the code we want to test.</p>
     */
    @Test
    public void oneSubscriberReceivesAMessage() {
        final Subscriber subscriber = context.mock(Subscriber.class);
        
        Publisher publisher = new Publisher();
        publisher.add(subscriber);

        final String message = "message";
        
        /*
         * Set expectations, e.g. That when we call publisher.publish(message)
         * A subscriber will call receive(message);
         */
        context.checking(new Expectations() {{ 
            oneOf(subscriber).receive(message); 
        }});
        
        // Execute the code we want to test
        publisher.publish(message);
    }
    
}

