package com.vc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetTest {

  public static void main(String[] args) {
    ServerSocket ss = null;
    try {
      ss = new ServerSocket(8080);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    while (true) {
      Socket so = null;
      try {
        so = ss.accept();
      }
      catch (IOException e) {
        System.out.println("客户端端来了额");
      }
      ReceiveThread t = new NetTest().new ReceiveThread();
      t.setSo(so);
      new Thread(t).start();
    }
  }

  public class ReceiveThread implements Runnable {

    private Socket so = null;

    @Override
    public void run() {
      while (true) {
        BufferedReader br = null;
        try {
          br = new BufferedReader(new InputStreamReader(so.getInputStream()));
        }
        catch (IOException e1) {
          System.out.println("客户端断开连接");
        }
        String str = null;
        try {
          str = br.readLine();
        }
        catch (IOException e) {
          System.out.println("客户端断开连接");
        }
        finally {
          try {
            br.close();
          }
          catch (IOException e) {
            System.out.println("客户端断开连接");
          }
        }
        System.out.println("客户端发来：" + so + "--" + so.getPort() + str);
      }
    }

    public Socket getSo() {
      return this.so;
    }

    public void setSo(Socket so) {
      this.so = so;
    }

  }

}
