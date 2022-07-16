package com.mf.java.core.lock;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest<T> {

    private HashMap<T, Integer> map = new HashMap<>();
    final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock r = readWriteLock.readLock();
    Lock w = readWriteLock.writeLock();

    public int getVal (T key){
        Integer value = null;
        r.lock();
        try {
            value = map.get(key);
            if (value != null){
                return value;
            }
        } finally {
            r.unlock();
        }
        w.lock();

        try {
            value = map.get(key);
            if (value == null){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Random random = new Random();
                value = random.nextInt(1000);
                map.put(key, value);
            }
        } finally {
            w.unlock();
        }
        return value;
    }

    public static void main(String[] args) {
        ReadWriteLockTest test = new ReadWriteLockTest();

        int size =100;
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    int val = test.getVal("zp");
                    System.out.println(val);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }).start();
            latch.countDown();
        }
    }
}
