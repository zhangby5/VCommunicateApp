package com.vc.test;

public class MyTest {
  
  public static void main(String[] args) throws ClassNotFoundException {
    ClassLoader loader = MyTest.class.getClassLoader();
    System.out.println(loader);
    System.out.println(loader.getParent());
    // 使用ClassLoader.loadClass()来加载类，不会执行初始化块
    // loader.loadClass("com.vc.test.Test2");
    // 使用Class.forName()来加载类，默认会执行初始化块
    // Class.forName("com.vc.test.Test2");
    // 使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
//    Class.forName("com.vc.test.Test2", false, loader);
    if(true){
      System.out.println(Test2.AAA);
    }
  }
}
