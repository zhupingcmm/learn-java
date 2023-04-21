package com.mf.java.core.threads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Demo06Volatile {
    public static void main(String[] args) throws InterruptedException {
        VolatileDemo volatileDemo = new VolatileDemo();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(volatileDemo);
            t.start();
        }

        Thread.sleep(100);
        System.out.println("count : " + volatileDemo.getCount());
    }

    static class VolatileDemo implements Runnable {
//        private volatile int count = 0;
        private AtomicInteger count = new AtomicInteger();
        private ReentrantLock lock = new ReentrantLock();

        public int getCount() {
            return count.get();
        }

        @Override
        public void run() {
            addCount();
        }

        // 使用 synchronized 关键字
//        private  synchronized void addCount() {
//            for (int i = 0; i < 10000; i++) {
//                count++;
//            }
//        }
        // 使用 可重入锁 ReentrantLock
//        private void addCount() {
//            for (int i = 0; i < 10000; i++) {
//                lock.lock();
//                count++;
//                lock.unlock();
//            }
//        }

        // 使用 原子类
        private void addCount() {
            for (int i = 0; i < 10000; i++) {
              count.incrementAndGet();
            }
        }
    }

}
