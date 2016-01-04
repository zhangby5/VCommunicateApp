package com.vc.test.classes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoad extends ClassLoader {

  private String name;

  private String path = "E:\\src";

  public MyClassLoad(String name) {
    this.name = name;
  }

  public MyClassLoad(ClassLoader parent, String name) {
    super(parent);
    this.name = name;
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    byte[] data = this.loadClassData(name);
    return this.defineClass(name, data, 0, data.length);
  }

  private byte[] loadClassData(String name) {
    name = name.replace(".", "\\");
    FileInputStream input = null;
    ByteArrayOutputStream ba = null;
    try {
      input = new FileInputStream(new File(path + name + ".class"));
      ba = new ByteArrayOutputStream();
      int b = 0;
      while ((b = input.read()) != -1) {
        ba.write(b);
      }
      return ba.toByteArray();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        input.close();
        ba.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;

  }
}
