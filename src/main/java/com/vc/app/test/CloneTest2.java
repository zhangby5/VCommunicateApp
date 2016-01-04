package com.vc.app.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CloneTest2 implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 4399317427429587299L;

  public static void main(String[] args) throws Exception {

    // 读取文件
    File file = new File("C:/Users/suning/Desktop/log.txt");
    FileInputStream fi = new FileInputStream(file);
    byte[] b = new byte[fi.available()];
    fi.read(b);
    String str = new String(b);
    System.out.println(str);
    fi.close();
    // 复制文件
    FileInputStream fic =
        new FileInputStream("C:/Users/suning/Desktop/log2.txt");
    FileOutputStream fot =
        new FileOutputStream("C:/Users/suning/Desktop/log3.txt");
    int length = 0;
    byte[] bb = new byte[1024];
    while ((length = fic.read(bb)) > 0) {
      fot.write(bb, 0, length);
    }
    fic.close();
    fot.close();

    CloneTest2 test = new CloneTest2();
    People p1 = test.new People("zhangby5", 25, new Address[] {
      test.new Address("aaaa")
    });

    People p2 = (People) p1.clone();
    People p3 = (People) test.new DeepClone().deepClone(p1);
    p1.addresses[0].setAddress("bbbb");
    System.out.println(p1.getAddresses()[0].getAddress());
    System.out.println(p2.getAddresses()[0].getAddress());
    System.out.println(p3.getAddresses()[0].getAddress());
  }

  public class People implements Cloneable, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -195335771704065118L;

    private String name;

    private int age;

    private Address[] addresses;

    public People(String name, int age, Address[] addresses) {
      super();
      this.name = name;
      this.age = age;
      this.addresses = addresses;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
      return super.clone();
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return this.age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public Address[] getAddresses() {
      return this.addresses;
    }

    public void setAddresses(Address[] addresses) {
      this.addresses = addresses;
    }

  }

  public class Address implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3785576037136217237L;

    private String address;

    public String getAddress() {
      return this.address;
    }

    public Address(String address) {
      super();
      this.address = address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

  }

  public class DeepClone {

    public Object deepClone(Object obj) {

      ObjectOutput objOut = null;
      ObjectInput oi = null;
      try {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        objOut = new ObjectOutputStream(bao);
        objOut.writeObject(obj);

        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        oi = new ObjectInputStream(bis);
        return oi.readObject();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      finally {
        try {
          objOut.close();
          oi.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }

      return null;
    }

  }

}
