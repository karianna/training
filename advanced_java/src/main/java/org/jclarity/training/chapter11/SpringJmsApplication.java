package org.jclarity.training.chapter11;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJmsApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try(AbstractApplicationContext context
            = new ClassPathXmlApplicationContext(new String []{"applicationContext.xml"})) {

            // Add a shutdown hook for the above context... 
            context.registerShutdownHook();
        }
    }

}
