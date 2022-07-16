package com.mf.java.core.sync;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

public class SyncThread1 {
    @Getter
    private volatile int count = 0;
    public synchronized void increment() {count ++;}

    public static void main(String[] args) throws InterruptedException {
        SyncThread1 s1 = new SyncThread1();
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {

           Thread thread = new Thread(() ->  {
               try {
                   latch.await();
                   s1.increment();
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
           });
           thread.start();
//           thread.join();
           latch.countDown();
        }

        Thread.sleep(10000);
        System.out.println("count is: " + s1.getCount());
    }
}
