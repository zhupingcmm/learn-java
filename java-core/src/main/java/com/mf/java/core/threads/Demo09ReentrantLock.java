package com.mf.java.core.threads;

import java.util.concurrent.locks.ReentrantLock;

public class Demo09ReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        VolatileDemo demo = new VolatileDemo();

        for (int i = 0; i < 2; i++) {
            Thread t1 = new Thread(demo);
            t1.start();
        }



        Thread.sleep(100);

        System.out.println(demo.getCount());

    }

    static class VolatileDemo implements Runnable{

        private volatile int count = 0;

        public int getCount() {
            return count;
        }

        ReentrantLock lock = new ReentrantLock();
        @Override
        public void run() {
            addCount();
        }

        private void addCount() {
            for (int i = 0; i < 100000; i++) {
                lock.lock();
                count++;
                lock.unlock();
            }

        }
    }
}
