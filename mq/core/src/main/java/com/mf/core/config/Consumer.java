package com.mf.core.config;

import com.mf.core.dto.OrderDto;
import com.mf.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.TimerTask;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class Consumer extends TimerTask {

    private final OrderService orderService;

    @Override
    public void run() {
        log.info("===== start to check db ==========");
        OrderDto orderDto = OrderDto.builder().id(1l).status(true).build();
        orderService.consumerOrder(orderDto);
    }
}
