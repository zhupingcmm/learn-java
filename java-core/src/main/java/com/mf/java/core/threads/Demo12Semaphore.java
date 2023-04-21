package com.mf.java.core.threads;

import java.util.concurrent.Semaphore;

public class Demo12Semaphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 获取到了车位");
                    Thread.sleep(3000);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " 离开了车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }
    }
}
