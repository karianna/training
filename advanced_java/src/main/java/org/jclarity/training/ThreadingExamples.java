package org.jclarity.training;

public class ThreadingExamples {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ThreadingExamples tx = new ThreadingExamples();
		tx.run();
	}

	private void compute() {
		// TODO Auto-generated method stub
		
	}

	
	private void run() {
		final Object r1 = new Object();
		final Object r2 = new Object();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				synchronized(r1) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) { }
					synchronized(r2) { compute(); }
				}
			}

		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				synchronized(r2) {
					synchronized(r1) { compute(); }
				}
			}
		});
		t1.start(); 
		t2.start(); 	
	}

}
