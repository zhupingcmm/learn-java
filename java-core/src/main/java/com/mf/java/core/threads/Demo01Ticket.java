package com.mf.java.core.threads;

import java.util.concurrent.locks.ReentrantLock;

public class Demo01Ticket {
    public static void main(String[] args) {
        SellTicketTask task = new SellTicketTask();

        Thread t1 = new Thread(task, "窗口1");
        Thread t2 = new Thread(task, "窗口2");
        Thread  t3 = new Thread(task, "窗口3");
        t1.start();
        t2.start();
        t3.start();
    }

    static class SellTicketTask implements Runnable{

        private int  tickets = 100;
        private final Object lock = new Object();
        private ReentrantLock reentrantLock = new ReentrantLock();

        @Override
        public void run() {
            while (true) {
//                synchronized (lock) {
                reentrantLock.lock();
                    if (tickets > 0) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(Thread.currentThread().getName() + "-正在卖： " + tickets--);
                    }
//                }
                reentrantLock.unlock();
            }
        }
    }
}


