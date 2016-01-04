package com.vc.app.test;

import java.util.PriorityQueue;

public class PRModelTest {

  private int queuesize = 10;

  private PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

  public static void main(String[] args) {
    PRModelTest t = new PRModelTest();
    t.new Consumer().start();
    t.new Produce().start();
  }

  public class Consumer extends Thread {

    @Override
    public void run() {
      while (true) {
        synchronized (queue) {
          while (queue.size() == 0) {
            try {
              System.out.println("队列已空，等待数据");
              queue.wait();
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          queue.poll();
          queue.notify();
          System.out.println("从队列中取走了一个，剩余：" + queue.size());
        }
      }
    }
  }

  public class Produce extends Thread {

    @Override
    public void run() {
      while (true) {
        synchronized (queue) {
          while (queue.size() == queuesize) {
            try {
              System.out.println("队列已满，等待空余空间");
              queue.wait();
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          queue.offer(new Integer(1));
          queue.notify();
          System.out.println("向队列中添加了一个，剩余：" + queue.size());
        }
      }
    }
  }

}
