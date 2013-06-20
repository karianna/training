package org.jclarity.training;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExamples {

	final Lock lock = new ReentrantLock();
	final Condition notFull  = lock.newCondition(); 
	final Condition notEmpty = lock.newCondition(); 

	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	//Thread 1
	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length)
				notFull.await();
			items[putptr] = x;
			if (++putptr == items.length) putptr = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	// Thread 2
	public Object take() throws InterruptedException {
		lock.lock();
		try {
			// If empty, release the lock & wait
			while (count == 0) notEmpty.await();
			
			Object x = items[takeptr];
			if (++takeptr == items.length) takeptr = 0;
			--count;
			
			// Signal notFull that it can stop waiting & try for the lock
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
