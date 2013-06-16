package org.jclarity.training;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicHTTPServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int port = Integer.parseInt(args[0]); 
		
			ServerSocket ss = new ServerSocket(port); 
			for(;;) {
				Socket client = ss.accept();
				HTTPHandler hndlr = new HTTPHandler(client);
				new Thread(hndlr).start();
			} 
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Usage: javaBasicHTTPServer <port>;");
		}
	}
		
	private static class HTTPHandler implements Runnable {
		Socket sock;
		HTTPHandler(Socket client) { this.sock = client; }
		
		public void run() {
			try (BufferedReader in =
					new BufferedReader(new InputStreamReader(sock.getInputStream()));
				 PrintWriter out =
					new PrintWriter(new OutputStreamWriter(sock.getOutputStream())); ) {
				out.print("HTTP/1.0 200\r\nContent-Type: text/plain\r\n\r\n");

				String line;
				while((line = in.readLine()) != null) {
					if (line.length() == 0) break;
					out.println(line);
				}
			} catch(Exception e) { 
				// Handle exception
			}
		}
	}
}
