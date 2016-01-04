package com.vc.app.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {

  private volatile int i = 0;

  private Lock lock = new ReentrantLock();
  
  private void increase() {
    lock.lock();
    i++;
    lock.unlock();
  }

  public static void main(String[] args) {
    final VolatileTest test = new VolatileTest();
    for (int i = 0; i < 10; i++) {
      new Thread(new Runnable() {
        public void run() {
          for (int i = 0; i < 100000; i++) {
            test.increase();
          }
        }
      }).start();
    }

    while (Thread.activeCount() > 1) {
      Thread.yield();
    }
    System.out.println(test.i);
  }

}
