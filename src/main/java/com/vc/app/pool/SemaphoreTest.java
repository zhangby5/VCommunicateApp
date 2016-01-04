package com.vc.test.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

  public static void main(String[] args) {
    ExecutorService pool = Executors.newCachedThreadPool();
    final Semaphore lock = new Semaphore(5);
    for (int i = 0; i < 100; i++) {
      final int tid = i;
      Runnable run = new Runnable() {

        @Override
        public void run() {
          try {
            lock.acquire();
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName() + "执行" + tid);
          try {
            Thread.sleep(100);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
          lock.release();
        }
      };
      pool.execute(run);
    }
    pool.shutdown();
  }

}
