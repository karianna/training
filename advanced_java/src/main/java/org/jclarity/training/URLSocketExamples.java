package org.jclarity.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class URLSocketExamples {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URLSocketExamples urlsx = new URLSocketExamples();
		urlsx.urlBasic();
		urlsx.urlAdvanced();
	}

	public void urlBasic() {
		try {
			URL url = new URL("http://www.jclarity.com/");
			try (InputStream in = url.openStream()) {
				Files.copy(in, Paths.get("output.txt"));
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void socketBasic() {
		String hostname = "www.jclarity.com"; 
		int port = 80;
		String filename = "/index.html";
		
		try (Socket sock = new Socket(hostname, port);
		     BufferedReader from = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			 PrintWriter to = new PrintWriter(new OutputStreamWriter(sock.getOutputStream())); ) {

			to.print("GET " + filename + " HTTP/1.0\r\n\r\n");
			to.flush();
		
			for(String l = null; (l = from.readLine()) != null; )
				System.out.println(l);
		
		} catch (IOException e) {
			// Deal with exceptionß
		}

	}
	
	public void urlAdvanced() {
		
		try {
			URL url = new URL("http://www.jclarity.com/"); 
			URLConnection conn = url.openConnection();

			String type = conn.getContentType();
			String encoding = conn.getContentEncoding();
			Date lastModified = new Date(conn.getLastModified());
			int len = conn.getContentLength();

			InputStream in = conn.getInputStream();
			System.out.println("Type: "+ type +" ; encoding: "+ encoding +" ; modified: "+ lastModified +" ; Length: "+ len);
		} catch (IOException e) {
			// Handle exception
		}

	}
	
}
