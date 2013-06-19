package org.jclarity.training;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceNumber {

	private final AtomicLong sequenceNumber = new AtomicLong(0);

	public long nextId() {
	  return sequenceNumber.getAndIncrement();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SequenceNumber aex = new SequenceNumber();
		aex.run();
	}
	
	private void run() {
		// 
	}

}
