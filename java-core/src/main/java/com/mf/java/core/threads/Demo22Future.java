package com.mf.java.core.threads;

import java.util.Random;
import java.util.concurrent.*;

public class Demo22Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       ExecutorService service = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
       Future<Integer> future = service.submit(new CallableTask());
        System.out.println(future.isDone());
        System.out.println(future.get());
        System.out.println("a");
        service.shutdown();
    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
