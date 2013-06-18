package org.jclarity.training.chapter11;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJmsApplication {
    
    private final static String[] CONFIGURATION = new String []{"applicationContext.xml"};
    
    /**
     * Main method to kick off a stand alone Spring application
     */
    public static void main(String[] args) {
        
        try(AbstractApplicationContext context 
                = new ClassPathXmlApplicationContext(CONFIGURATION)) {

            // Add a shutdown hook for the above context. 
            context.registerShutdownHook();
        }
    }

}
