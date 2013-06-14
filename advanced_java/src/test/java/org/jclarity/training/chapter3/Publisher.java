package org.jclarity.training.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * Registers subscribers and publishes messages
 */
public class Publisher {

    List<Subscriber> subscribers = new ArrayList<>();
    
    /**
     * Add a Subscriber 
     * @param subscriber
     */
    public void add(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Publish a message
     * @param message
     */
    public void publish(String message) {
        subscribers.get(0).receive(message);
    }

}
