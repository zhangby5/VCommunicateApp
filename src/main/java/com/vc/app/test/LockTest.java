package com.vc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

  private Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    final LockTest t = new LockTest();
    new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          t.process(Thread.currentThread().getName());
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();

    Thread t1 = new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          t.process(Thread.currentThread().getName());
        }
        catch (InterruptedException e) {
          System.out.println(2222);
        }
      }
    });
    t1.start();
    try {
      Thread.sleep(10);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void process(String ThreadName) throws InterruptedException {
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + "获得了锁");
      Thread.sleep(1000);
    }
    catch (Exception e) {
      System.out.println(1111);
    }
    finally {
      lock.unlock();
    }
  }

}
