package com.mf.java.core.sync;

import java.util.concurrent.CountDownLatch;

public class SyncThread2 {
    private volatile static int count;
    public static synchronized void increment(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 100;
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    SyncThread2.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            latch.countDown();
        }


        Thread.sleep(10000);
        System.out.println(SyncThread2.count);
    }
}
