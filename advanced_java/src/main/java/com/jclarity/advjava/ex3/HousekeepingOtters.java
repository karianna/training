package com.jclarity.advjava.ex3;

/**
 * This tool should allow the user to specify 2 parameters - the Otter to
 * move and the new location where their record should be put. 
 * 
 * Yes, most operating systems already have copy functionality. But the 
 * Diabolical Developer prefers to do everything in Java! 
 * 
 * Why? There is no "why". That's why it's Diabolical! 
 */
public class HousekeepingOtters {

    /**
     * Implement the move using the Files class
     *
     * @param args location of Otter, destination of Otter
     */
    public static void main(String[] args) {
    	if (args.length < 2) usage();
    	
    	final String src = args[0];
    	final String dst = args[1];
    	
        // HINT: The Diabolical Developer has provided some sample otters 
        // in resources/data, you should practice with these and the Path and Files
        // class.
        
        // Bonus points for dealing with incorrect Paths!
        
        // Further bonus points for reading and displaying the contents of the 
        // Otter files
    }

	private static void usage() {
		System.out.println("Usage: HousekeepingOtters <src> <dst>");
		System.exit(-1);
	}
}
