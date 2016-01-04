package com.vc.app.test.classes;

public class ClassLoadTest {

  public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

    MyClassLoad cl = new MyClassLoad("myclassload");
    Class<?> clazz = cl.loadClass("com.vc.test.classes.StringUtil");
    StringUtil util = (StringUtil) clazz.newInstance();
    System.out.println(util.CONST);

  }

}
