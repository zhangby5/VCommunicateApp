package com.vc.app.test;

public class ThreadTest {

  private static final String obj = "LOCK";

  public static void main(String[] args) {
    ThreadTest t = new ThreadTest();
    new Thread(t.new ThreadA()).start();
    new Thread(t.new ThreadC()).start();
    try {
      Thread.sleep(1000);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    new Thread(t.new ThreadB()).start();
  }

  public class ThreadA implements Runnable {

    public void run() {
      synchronized (obj) {
        try {
          Thread.sleep(100);
          obj.wait();
          System.out.println("11111");
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }
  }

  public class ThreadB implements Runnable {

    public void run() {
      synchronized (obj) {
        try {
          Thread.sleep(100);
          obj.notifyAll();
          System.out.println("2222");
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }
  }

  public class ThreadC implements Runnable {

    public void run() {
      synchronized (obj) {
        try {
          Thread.sleep(100);
          obj.wait();
          System.out.println("3333");
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }
  }

}
