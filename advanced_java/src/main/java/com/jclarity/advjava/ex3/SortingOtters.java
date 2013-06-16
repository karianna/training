package com.jclarity.advjava.ex3;

/**
 * Implement sortOtters
 */
public class SortingOtters {
    
    private Otter[] otters = new Otter[3];
    
    public SortingOtters() {
        otters[0] = new Otter("Ben", 10);
        otters[1] = new Otter("Ben", 8);
        otters[2] = new Otter("Martijn", 10);
    }
    
    /**
     * Implement this method so that is returns a list of sorted Otters.
     * Otters should be sorted by name and then tail length from shortest 
     * to longest.
     */
    public Otter[] sortOtters() {
        // TODO implement the sort using Arrays class & by implementing the Comparator
        return getOtters();
    }

    /**
     * @return the otters
     */
    public Otter[] getOtters() {
        return otters;
    }

}
