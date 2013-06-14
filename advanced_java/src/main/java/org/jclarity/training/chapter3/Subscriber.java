package org.jclarity.training.chapter3;

/**
 * Interface that represents a subscriber
 */
public interface Subscriber {
    
    /**
     * Receive a String message
     * @param message
     */
    public void receive(String message);
}