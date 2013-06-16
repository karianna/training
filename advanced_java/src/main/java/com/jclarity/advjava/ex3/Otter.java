package com.jclarity.advjava.ex3;

/**
 * 
 * @author karianna
 */
public class Otter {
    
    private final String name;
    
    private final int tailLength;

    public Otter(String name, int tailLength) {
        this.name = name;
        this.tailLength = tailLength;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the tailLength
     */
    public int getTailLength() {
        return tailLength;
    }

}
