/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jclarity.advjava.ex8;

/**
 *
 * @author boxcat
 *
 */
public class WorkUnit<T> {
  private final T workUnit;

  public T getWork() { return workUnit; }

  public WorkUnit(T workUnit_) {
    workUnit = workUnit_;
  }
}