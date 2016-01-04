package com.vc.test;
public class Test2 {
  
  public static String AAA = "AAA";
  
	static {
		System.out.println("静态初始化块执行了！");
	}

	public static void testmethod() {
		System.out.println("静态方法执行了！");
	}
}
