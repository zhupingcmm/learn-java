package com.mf.java.core.threads;

import java.util.concurrent.CountDownLatch;

public class Demo11CountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <= 6; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "上完班了，离开公司！");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }
        new Thread(() -> {
            try {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName()+"\t卷王最 后关灯走人");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "7").start();
    }

}
