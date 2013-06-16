package org.jclarity.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
* Maintains a set of counters. Counters are uniquely identified by a  
<code>String</code> key, and can be incremented,
* and their value retrieved. Listeners can be added for a specific  
counter which will return the value of a counter
* when it is incremented.
*
* This class is intended to be thread-safe.
*
*/

public class SaferCounters {

	// Stores the counters and their associated counts
	private Map<String, AtomicLong> storage;
	
	private Map<String, ArrayList<SaferCountListener>> listeners;
	
	public SaferCounters() {
		storage = Collections.synchronizedMap(new HashMap<String, AtomicLong>());
		listeners = Collections.synchronizedMap(new HashMap<String, ArrayList<SaferCountListener>>());
	}
	
	/**
	 * Increments the specified counter, notifying any listeners
	 * @param counter The key of the counter to increment
	 */
	public void increment(String counter) {
		AtomicLong al = storage.get(counter);
		if (al == null) al = setupNewCounter(counter);
		
		synchronized (al) {
			al.incrementAndGet();
			al.notifyAll();
		}
	}

	/**
	 * Helper method to avoid variant of DCL. 
	 * 
	 * Throws IllegalStateException if (somehow) the listeners for a counter key are setup, but not the actual atomic counter
	 */
	synchronized AtomicLong setupNewCounter(String counter) {
		if (storage.get(counter) != null) return storage.get(counter);
		
		AtomicLong al = new AtomicLong();
		storage.put(counter, al);
		
		// Now do the listeners
		if (listeners.get(counter) != null) throw new IllegalStateException("Counter: "+ counter +" in illegal state - listeners already setup");
		ArrayList<SaferCountListener> list = new ArrayList<SaferCountListener>();
		listeners.put(counter, list);
		
		// Return the long
		return al;
	}
	
	/**
	 * Gets the count for the specified key
	 * @param counter The key of the counter
	 * @return The count for the specified counter key
	 */
	public long getCount(String counter) {
		return storage.get(counter).get();
	}
	
	/**
	 * Resets all of the counters managed by this <code>Counters</code> object to zero.
	 */
	public synchronized void reset() {
		// This can take an arbitrary length of time, depending on the size of the valueset of storage.
		// This is the simplest safe way of doing it - more advanced approaches (eg versioned iterators) 
		// could be used, but recovery strategies would add complexity - this should be our first step
		// unless testing shows that it would be unwieldy for our data set size.
		for (AtomicLong al : storage.values()) {
			al.set(0);
		}
	}

	/**
	 * Adds a listener. The listener is notified when its counter is incremented
	 * @param listener The listener to add
	 */
	public void addListener(SaferCountListener listener) {
		String counter = listener.getCounter();
		
		List<SaferCountListener> list = listeners.get(counter);
		synchronized (list) {	
			list.add(listener);
		}
	}
	
	/**
	 * A listener for a specific counter.
	 *
	 * This class is intended to be thread-safe.
	 */
	public class SaferCountListener {
		private String counter;
		/**
		 * Creates a <code>CountListener</code> which listens for increments to the specified counter.
		 * @param counter The counter to listen for
		 */
		public SaferCountListener(String counter) {
			this.counter = counter;
		}
		
		// Helper method
		public String getCounter() {
			return counter;
		}
		
		/**
		 * Waits for an increment to occur to this listener's counter. This method will return the value of the counter only
		 * when an increase occurs to this counter (or the calling thread is interrupted)
		 * @return The value of the counter
		 * @throws InterruptedException When the calling thread is interrupted
		 */
		public long waitForIncrement() throws InterruptedException {
			AtomicLong al = storage.get(counter);
			if (al == null) throw new IllegalStateException("Attempted to wait() on not-setup counter "+ counter);
			
			synchronized (al) {
				// Occasionally helpful for debug
//				System.out.println("Waiting on "+ al.hashCode());			
				al.wait();				
			}
			
			// Note that this could potentially give rise to "lost update" if another 
			// thread increments the counter again very quickly after the first update.
			// That is, a sequence of calls to waitForIncrement(), while guaranteed to be 
			// monotonic increasing, is not guaranteed to be gap-free.
			return storage.get(counter).get();
		}
		
	}
	
}