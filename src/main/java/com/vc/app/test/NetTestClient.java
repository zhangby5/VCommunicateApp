package com.vc.app.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetTestClient {

  public static void main(String[] args) throws Exception {
    Socket so = new Socket("127.0.0.1", 8080);
    PrintWriter pw = new PrintWriter(so.getOutputStream());
    pw.print("哈哈");
    pw.flush();
    pw.close();
    ReceiveMsg rm = new NetTestClient().new ReceiveMsg();
    rm.setSo(so);
    new Thread(rm).start();
  }
  
  public class ReceiveMsg implements Runnable {

    private Socket so;

    public void run() {

      while (so.isConnected()) {
        InputStreamReader input = null;
        try {
          input = new InputStreamReader(so.getInputStream());
          BufferedReader br = new BufferedReader(input);
          String str = br.readLine();
          System.out.println("服务器发来：" + str);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        finally {
          try {
            input.close();
          }
          catch (IOException e) {
            e.printStackTrace();
          }
        }

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
