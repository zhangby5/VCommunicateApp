package com.vc.test.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

  public static void main(String[] args) {
    ExecutorService pool = Executors.newCachedThreadPool();
    final CountDownLatch cd = new CountDownLatch(5);
    for (int i = 0; i < 10; i++) {
      Runnable run = new Runnable() {

        @Override
        public void run() {
          System.out.println("要等待么？");
          try {
            cd.await();
            Thread.sleep(100);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("可以啦！");
        }
      };
      pool.execute(run);
    }

    for (int i = 0; i < 5; i++) {
      final int tid = i;
      Runnable samrun = new Runnable() {

        @Override
        public void run() {
          System.out.println("我是第" + tid + "个");
          try {
            cd.countDown();
            Thread.sleep(800);
          }
          catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
      pool.execute(samrun);
    }
    pool.shutdown();
  }

}
