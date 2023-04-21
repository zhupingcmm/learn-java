package com.mf.java.core.threads;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Demo10ReentrantReadWriteLock {

    private static volatile int count = 0;

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReadDemo readDemo = new ReadDemo(readWriteLock);
        WriteDemo writeDemo = new WriteDemo(readWriteLock);

        for (int i = 0; i < 5; i++) {
            new Thread(readDemo).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(writeDemo).start();
        }

    }


    static class ReadDemo implements Runnable {
        ReentrantReadWriteLock lock;

        public ReadDemo(ReentrantReadWriteLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10);
                lock.readLock().lock();
                System.out.println(" read count::" + count);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.readLock().unlock();
            }

        }
    }

    static class WriteDemo implements Runnable {
        ReentrantReadWriteLock lock;

        public WriteDemo(ReentrantReadWriteLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(10);
                    lock.writeLock().lock();
                    count++;
                    lock.writeLock().unlock();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
          ;
            }
        }
    }
}
