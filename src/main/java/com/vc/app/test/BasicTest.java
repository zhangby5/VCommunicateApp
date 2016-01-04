package com.vc.app.test;

public class BasicTest {

  private static final int COUNT_BITS = Integer.SIZE - 3;

  private static final int RUNNING = -1 << COUNT_BITS;

  private static final int CAPACITY = (1 << COUNT_BITS) - 1;

  public static void main(String[] args) {
    System.out.println(CAPACITY);
    System.out.println(COUNT_BITS);
    System.out.println(RUNNING);
    System.out.println(Integer.toBinaryString(RUNNING));
    int i = RUNNING | 2;
    System.out.println(i);
    System.out.println(Integer.toBinaryString(i));


  }

}
