package com.vc.app.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTypeTest {
  private Lock lock = new ReentrantLock();
  public static void main(String[] args) {
    final LockTypeTest t = new LockTypeTest();
    for (int i = 0; i < 1; i++) {
      final int j = i;
      new Thread(new Runnable() {

        public void run() {
          t.process(j);
        }
      }).start();
    }

  }

  public void process(int i) {
    lock.lock();
    process2(i);
    lock.unlock();
  }

  public void process2(int i) {
    lock.lock();
    System.out.println(i);
    lock.unlock();
  }

}
