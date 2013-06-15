package org.jclarity.training;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilesExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FilesExample fx = new FilesExample();
		fx.run();
	}

	private void run() {
		File homedir = new File(System.getProperty("user.home"));
		File f = new File(homedir, ".configfile");
		
		if (f.exists() && f.isFile() && f.canRead()) {
			File configdir = new File(homedir, ".configdir");
			configdir.mkdir();
			f.renameTo(new File(configdir, ".config")); 
		}

		String[] allfiles = homedir.list();
		
		
		String filename = System.getProperty("user.home") + File.separator + ".cshrc";
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line;
			
			while((line = in.readLine()) != null) {
				System.out.println(line); 
			}
			in.close(); 
		} catch (IOException e) {
			// Handle FileNotFoundException, etc. here
		}

	}
}
