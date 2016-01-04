package com.vc.app.test;

import java.util.Map;

public class LinkedHashMapTest {

  public static void main(String[] args) {
    LRUCach<String, String> map = new LRUCach<String, String>(2);
    map.put("A", "a");
    map.put("B", "b");
    map.put("C", "c");
    map.put("D", "d");
    map.get("C");
    for (Map.Entry<String, String> e : map.getAll()) {
      System.out.println(e.getValue());
    }
  }
}
