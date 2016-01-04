package com.vc.app.test;

public class FinalTest {

  private static final People p = new FinalTest().new People();

  public static void main(String[] args) {
    System.out.println(p);
    p.setName("aaa");
    System.out.println(p);
  }
  
  public class People{
    private String name;

    
    public String getName() {
      return this.name;
    }

    
    public void setName(String name) {
      this.name = name;
    }
    
  }
}
