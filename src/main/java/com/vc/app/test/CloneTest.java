package com.vc.test;


public class CloneTest {
  
  public static void main(String[] args) throws CloneNotSupportedException {
    CloneTest test = new CloneTest();
    People p1 = test.new People("zhangby5", 25, new Address[] {
        test.new Address("111")
    });
    People p2 = (People) p1.clone();
    
    p1.getAddress()[0].setAddress("aaaaa");
    
    System.out.println(p1.address[0].getAddress());
    System.out.println(p2.address[0].getAddress());
    
  }
  
  public class People implements Cloneable{
    
    private String name;
    
    private int age;
    
    private Address[] address;
    
    public People(String name, int age, Address[] address) {
      super();
      this.name = name;
      this.age = age;
      this.address = address;
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

    
    public Address[] getAddress() {
      return this.address;
    }

    
    public void setAddress(Address[] address) {
      this.address = address;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
      return super.clone();
    }
    
  }
  
  public class Address{
    
    private String address;
    
    public Address(String address) {
      super();
      this.address = address;
    }


    public String getAddress() {
      return this.address;
    }

    
    public void setAddress(String address) {
      this.address = address;
    }
    
  }
  
  public class DeepClone{
    
  }

}
