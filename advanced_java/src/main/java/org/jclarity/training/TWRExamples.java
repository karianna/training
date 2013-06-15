package org.jclarity.training;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TWRExamples {

	private void getURL7() throws MalformedURLException {	
		URL url = new URL("http://www.java7developer.com/blog/?page_id=97");

		try (InputStream in = url.openStream()) {
			Files.copy(in, Paths.get("output.txt"));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	private void getFile7() throws MalformedURLException {
		File inputFile = new File("output.txt");
		try (InputStream in = new FileInputStream(inputFile)) {
			Files.copy(in, Paths.get("output.txt"));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void getURL6() throws MalformedURLException {
		URL url = new URL("http://www.java7developer.com/blog/?page_id=97");
		InputStream is = null;
		try {
			is = url.openStream();
		} catch(IOException iox) {
			return;
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File("output.txt"));

			byte[] buf = new byte[4096];
			int len;
			while ((len = is.read(buf)) > 0) {
				fos.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    if (fos != null) {
		        try { fos.close(); } catch (IOException e) { }
		        if (is != null) {
		            try { is.close(); } catch (IOException e) { }
		        }
		    }
		}	
	}
}
