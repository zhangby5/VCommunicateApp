package com.vc.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPrintTest {

  private String lock = "lock";

  private ReentrantLock locks = new ReentrantLock();

  private Condition con = locks.newCondition();

  private static int nextnum = 0;

  private volatile static int currentnum = 0;

  private static int allnum = 100;

  public static void main(String[] args) {
    ThreadPrintTest t = new ThreadPrintTest();
    // new Thread(t.new ThreadA()).start();
    // new Thread(t.new ThreadB()).start();

    for (int i = 0; i < 10; i++) {
      new Thread(t.new ThreadC(i)).start();
    }
  }

  public class ThreadC implements Runnable {

    private int current;

    public ThreadC(int current) {
      super();
      this.current = current;
    }

    @Override
    public void run() {
      locks.lock();
      while (currentnum < allnum) {
        while (current != nextnum) {
          try {
            con.await();
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println(Thread.currentThread() + "打印了" + currentnum++);
        nextnum++;
        if (nextnum == 10) {
          nextnum = 0;
        }
        con.signalAll();
      }
      System.out.println(Thread.currentThread() + "-----" + currentnum);
      locks.unlock();
    }
  }

  public class ThreadA implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 100; i++) {
        synchronized (lock) {
          if (i % 2 == 0) {
            System.out.println("A线程打印：" + i);
            try {
              lock.wait();
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          lock.notify();
        }
      }
    }
  }

  public class ThreadB implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 100; i++) {
        synchronized (lock) {
          if (i % 2 != 0) {
            System.out.println("B线程打印：" + i);
            try {
              lock.wait();
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          lock.notify();
        }
      }
    }
  }

}
