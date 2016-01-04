package com.vc.app.test;

public class MyObjectClass {

  private static MyObjectClass instance = null;

  private MyObjectClass() {

  };

  public static MyObjectClass getInstance() {
    if (MyObjectClass.instance != null) {
      return instance;
    }
    try {
      Thread.sleep(100);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    synchronized (MyObjectClass.class) {
      if (MyObjectClass.instance != null) {
        return instance;
      }
      instance = new MyObjectClass();
      return instance;
    }
  }

}
