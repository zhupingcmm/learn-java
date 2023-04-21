package com.mf.java.core.threads;

import java.util.HashMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class Demo13CyclicBarrier {
    public static void main(String[] args) {

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("a", "b");

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("=====召唤圣龙");
        });

        for (int i = 0; i < 8 ; i++) {
            int finalI = i;
            new Thread(() -> {

                try {
                    System.out.println(Thread.currentThread().getName() + "获取到， 第" + finalI + "个龙珠");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "第" + finalI + "个龙珠飞走了");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }
    }
}
