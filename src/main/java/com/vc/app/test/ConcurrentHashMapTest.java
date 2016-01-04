package com.vc.test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

  private static ConcurrentHashMap<String, String> map =
      new ConcurrentHashMap<String, String>();

  static {
    map.put("A", "1");
    map.put("B", "2");
    map.put("C", "3");
    map.put("D", "4");
  }

  public static void main(String[] args) {
    final ConcurrentHashMapTest test = new ConcurrentHashMapTest();
    new Thread(new Runnable() {

      @Override
      public void run() {
        test.iteratorMap();
      }
    }).start();
    new Thread(new Runnable() {

      @Override
      public void run() {
        test.modifyMap();
      }
    }).start();
  }

  private void iteratorMap() {
    Set<String> key = map.keySet();
    Iterator<String> it = key.iterator();
    while (it.hasNext()) {
      String str = it.next();
      System.out.println(map.get(str));
      try {
        Thread.sleep(100);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void modifyMap() {
    map.remove("D");
  }
}
