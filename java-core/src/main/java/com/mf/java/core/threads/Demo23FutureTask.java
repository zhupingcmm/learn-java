package com.mf.java.core.threads;

import java.util.Random;
import java.util.concurrent.*;

public class Demo23FutureTask {

    public static void main(String[] args){
        ExecutorService executor = new ThreadPoolExecutor(5,10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executor.submit(futureTask);
        Integer res = null;
        try {
            res = futureTask.get(3, TimeUnit.SECONDS);
            System.out.println(res);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }



    }


    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
