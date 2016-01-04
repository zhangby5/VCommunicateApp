package com.vc.test;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {

  private static CopyOnWriteArrayList<String> strList =
      new CopyOnWriteArrayList<String>();

  public static void main(String[] args) {
    strList.add("A");
    strList.add("B");
    strList.add("C");
    strList.add("D");
    final CopyOnWriteArrayListTest test = new CopyOnWriteArrayListTest();
    new Thread(new Runnable() {

      @Override
      public void run() {
        test.interatorList();
      }
    }).start();
    new Thread(new Runnable() {

      @Override
      public void run() {
        test.modifyList();
      }
    }).start();
  }

  private void interatorList() {
    for (String str : strList) {
      try {
        Thread.sleep(100);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(str);
    }
  }

  private void modifyList() {
    strList.remove("C");
    System.out.println(strList.toString());
  }

}
