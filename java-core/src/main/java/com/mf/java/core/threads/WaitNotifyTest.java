package com.mf.java.core.threads;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

public class WaitNotifyTest <T>{
    LinkedList<T> queue = new LinkedList<>();

    public synchronized void put(T val) throws InterruptedException {
        if (queue.size() >=5) {
            System.out.println("producer: queue is full, can not insert resource");
            this.wait();
        }
        System.out.println("producer: insert " + val + "!!");
        queue.addLast(val);
        this.notify();
    }

    public synchronized T get() throws InterruptedException {
        if (queue.size() <= 0) {
            System.out.println("Consumer: queue is empty");
            this.wait();
        }

        T val = queue.poll();
        System.out.println("Consumer: take resource " + val);
        this.notify();
        return val;
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 2;
        CountDownLatch latch = new CountDownLatch(size);
        WaitNotifyTest<Integer> waitNotifyTest = new WaitNotifyTest<Integer>();

        Thread t1 = new Thread(() -> {
            latch.countDown();
            for (int i = 0; i < 10; i++) {
                try {
                    waitNotifyTest.put(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            latch.countDown();
            for (int i = 0; i < 10; i++) {
                try {
                    int val = waitNotifyTest.get();
                    System.out.println("get val: " + val);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
        latch.await();
        t1.join();
        t2.join();
        System.out.println("end");
    }
}
