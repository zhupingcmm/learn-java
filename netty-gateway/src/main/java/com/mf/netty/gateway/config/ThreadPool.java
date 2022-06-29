package com.mf.netty.gateway.config;

import lombok.Getter;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private static ThreadPool instance;
    @Getter
    private ThreadPoolExecutor service;
    private ThreadPool () {}
    public static ThreadPool getInstance(){
        if (instance == null) {
            synchronized (ThreadPool.class){
                if (instance == null) {
                    instance = new ThreadPool();
                }
            }
        }
        return instance;
    }

    public void init() {
        int cores = Runtime.getRuntime().availableProcessors();
        service = new ThreadPoolExecutor(
                cores, cores,
                5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );
    }
}
