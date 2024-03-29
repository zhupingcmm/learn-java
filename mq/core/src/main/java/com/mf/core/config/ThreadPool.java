package com.mf.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPool {
    @Bean
    public ThreadPoolExecutor poolExecutor() {
       return new ThreadPoolExecutor(
                5, 10,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20)
        );
//        return new ScheduledThreadPoolExecutor(5);
    }

}
