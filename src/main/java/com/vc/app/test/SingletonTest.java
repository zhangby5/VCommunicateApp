package com.vc.app.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class SingletonTest {

  public static void main(String[] args) throws InterruptedException {
    SingletonTest t = new SingletonTest();
    CountDownLatch cd = new CountDownLatch(1000);
    Set<MyObjectClass> objset =
        Collections.synchronizedSet(new HashSet<MyObjectClass>());
    while (objset.size() < 2) {
      List<Thread> list = new ArrayList<Thread>();
      for (int i = 0; i < 100; i++) {
        Thread td = new Thread(t.new ThreadT(objset, cd));
        list.add(td);
        td.start();
      }
      for (Thread tt : list) {
        tt.join();
      }
    }

    for (MyObjectClass obj : objset) {
      System.out.println(obj);
    }
  }

  public class ThreadT implements Runnable {

    private Set<MyObjectClass> objset = null;

    private CountDownLatch cd = null;

    public ThreadT(Set<MyObjectClass> objset, CountDownLatch cd) {
      super();
      this.objset = objset;
      this.cd = cd;
    }

    public void run() {
      this.objset.add(MyObjectClass.getInstance());
      cd.countDown();
    }
  }

}
