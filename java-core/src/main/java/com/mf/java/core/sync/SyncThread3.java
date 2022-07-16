package com.mf.java.core.sync;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

public class SyncThread3 {
    Object lock = new Object();
    @Getter
    private volatile int count =0;
    public void increment(){
        synchronized (lock){
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncThread3 syncThread3 = new SyncThread3();
        int size = 100;
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    syncThread3.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }).start();
            latch.countDown();
        }


        Thread.sleep(10000);
        System.out.println(syncThread3.getCount());
    }
}
