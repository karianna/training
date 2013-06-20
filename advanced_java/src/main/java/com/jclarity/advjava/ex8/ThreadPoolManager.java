/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jclarity.advjava.ex8;

import java.util.concurrent.*;

/**
 *
 * @author boxcat
 */
public class ThreadPoolManager {

  private final ScheduledExecutorService stpe;
  private final BlockingQueue<WorkUnit<String>> lbq;

  public ScheduledFuture<?> run(QueueReaderTask msgReader) {
    msgReader.setQueue(lbq);
    return stpe.schedule(msgReader, 10, TimeUnit.MILLISECONDS); 
  }

  public void cancel(final ScheduledFuture<?> hndl) {
    stpe.schedule(new Runnable() {
      public void run() { hndl.cancel(true); }
    }, 10, TimeUnit.MILLISECONDS);
  }

  public ThreadPoolManager(BlockingQueue<WorkUnit<String>> lbq_, int size) {
	  stpe = Executors.newScheduledThreadPool(size);  
	  lbq = lbq_;
  }  
}