package com.mf.nio.pool;

import java.util.concurrent.*;

public class PoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor service = new ThreadPoolExecutor(
                5, 5,
                5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );
        Runnable r = () -> {
            System.out.println("a");
            System.out.println(1/0);
        };
        Future future = service.submit(r);
        future.get();
        service.shutdown();


    }
}
