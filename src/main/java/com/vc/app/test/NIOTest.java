package com.vc.app.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {

  public static void main(String[] args) throws Exception {
    System.out.println(new NIOTest().testReturn());
  }
  
  public String testReturn(){
    String i = "zhang";
    try {
      i = "zhangby5";
      return i;
    }
    catch (Exception e) {
      i = "zhangby6";
      return i;
    }finally{
      i="zhangby7";
    }
  }

  public void write() throws Exception {
    FileOutputStream output =
        new FileOutputStream(new File("C:/Users/zhangby5/Desktop/fp.txt"));
    FileChannel ca = output.getChannel();
    ByteBuffer bb = ByteBuffer.allocate(1024);
    String str = "zhangby5";
    bb.put(str.getBytes());
    bb.flip();
    ca.write(bb);
    output.close();
    ca.close();
  }
  
  public void read() throws Exception{
    FileInputStream fi = new FileInputStream("C:/Users/zhangby5/Desktop/fp.txt");
    ByteBuffer bb = ByteBuffer.allocate(1024);
    FileChannel fc = fi.getChannel();
    fc.read(bb);
    bb.flip();
    while(bb.hasRemaining()){
      System.out.print((char)bb.get());
    }
  
  }

  public void testBuffer() {
    IntBuffer bf = IntBuffer.allocate(10);
    for (int i = 0; i < bf.capacity(); i++) {
      bf.put(i * 2);
    }
    bf.flip();
    while (bf.remaining() > 0) {
      System.out.print(bf.get() + " ");
    }
  }

}
