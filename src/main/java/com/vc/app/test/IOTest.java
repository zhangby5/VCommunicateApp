package com.vc.app.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class IOTest {
  
  public static void main(String[] args) throws IOException {
    
    FileInputStream input = new FileInputStream(new File("C:/Users/zhangby5/Desktop/fp12.txt"));
    try {
      byte[] b = new byte[1024];
      StringBuffer bf = new StringBuffer();
      while((input.read(b))>0){
        bf.append(new String(b));
      }
      System.out.println(bf.toString());
    }
    catch (IOException e) {
      input.close();
      e.printStackTrace();
    }
  }

}
