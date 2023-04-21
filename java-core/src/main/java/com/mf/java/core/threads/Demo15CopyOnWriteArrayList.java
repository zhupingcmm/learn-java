package com.mf.java.core.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo15CopyOnWriteArrayList {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("a");

        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>(Arrays.asList(1,2));

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(new ReadThread(copyOnWriteArrayList));
        executorService.submit(new WriteThread(copyOnWriteArrayList));
        executorService.submit(new WriteThread(copyOnWriteArrayList));
        executorService.submit(new WriteThread(copyOnWriteArrayList));
        executorService.submit(new ReadThread(copyOnWriteArrayList));
        executorService.submit(new ReadThread(copyOnWriteArrayList));
        executorService.submit(new WriteThread(copyOnWriteArrayList));
        executorService.submit(new WriteThread(copyOnWriteArrayList));

        Thread.sleep(10);
        System.out.println("copyList size:"+copyOnWriteArrayList.size());
        executorService.shutdown();


    }

    static class ReadThread implements Runnable {

        List<Integer> list;

        public ReadThread(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (Integer ele : list) {
                System.out.println(ele + ",");
            }
        }
    }

    static class WriteThread implements Runnable {
        List<Integer> list;

        public WriteThread(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            list.add(9);
        }
    }
}
