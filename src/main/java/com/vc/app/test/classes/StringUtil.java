package com.vc.test.classes;

public class StringUtil {

  public static String str = "TEST";

  public static final String CONST = "STRINGUTIL";
  
  static{
    System.out.println("INIT");
  }

  public StringUtil() {
    System.out.println("NEW");
  }

  public static boolean isNull(String str) {
    if (str == null || str.length() == 0) {
      return true;
    }
    return false;
  }

}
