package com.mf.core.config;

import com.mf.core.dto.OrderDto;
import com.mf.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@RequiredArgsConstructor
public class Executor {
    private final ThreadPoolExecutor poolExecutor;
    private final OrderService orderService;
    private final Consumer consumer;

//    @PostConstruct
    public void execute() {
        Timer timer = new Timer();

        System.out.println("poolExecutor:: " +  poolExecutor);
        timer.schedule(consumer, 3000, 1000);
    }
}
