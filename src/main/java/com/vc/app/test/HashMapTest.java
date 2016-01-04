package com.vc.app.test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
  
  public static void main(String[] args) {
    HashMapTest t = new HashMapTest();
    People p1 = t.new People("zhangby5", "aaa");
    People p2 = t.new People("hahahaha", "aaa");
    Map<HashMapTest.People, String> m =
        new HashMap<HashMapTest.People, String>();
    m.put(p1, "11111");
    m.put(p2, "22222");
    System.out.println(m.get(p1));
  }

  public class People {

    private String name;

    private String address;

    public People(String name, String address) {
      super();
      this.name = name;
      this.address = address;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      if (name.length() % 2 == 0) {
        return prime;
      }
      return 95;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      People other = (People) obj;
      if (!getOuterType().equals(other.getOuterType()))
        return false;
      if (name == null) {
        if (other.name != null)
          return false;
      }
      else if (!name.equals(other.name))
        return false;
      return true;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getAddress() {
      return this.address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    private HashMapTest getOuterType() {
      return HashMapTest.this;
    }

  }

}
