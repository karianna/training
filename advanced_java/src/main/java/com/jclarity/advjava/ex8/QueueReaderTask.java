/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jclarity.advjava.ex8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author boxcat
 */
public abstract class QueueReaderTask implements Runnable {
    private volatile boolean shutdown = false;
    protected BlockingQueue<WorkUnit<String>> q;
    private final int pollTime;
    
    public QueueReaderTask(int i) {
        pollTime = i;
    }
    
    public void shutdown() {
        shutdown = true;
    }
                
    public void run() {
      while (!shutdown) {
          try {
              WorkUnit<String> wu = q.poll(pollTime, TimeUnit.MILLISECONDS);
              if (wu != null) doAction(wu.getWork());
          } catch (InterruptedException e) {
              shutdown = true;
          }
      }
    }

    public abstract void doAction(String msg);
    
    public void setQueue(BlockingQueue<WorkUnit<String>> lbq) {
        q = lbq;
    }
}
