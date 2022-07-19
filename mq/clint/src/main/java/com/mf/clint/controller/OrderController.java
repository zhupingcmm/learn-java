package com.mf.clint.controller;

import com.mf.clint.dto.OrderDto;
import com.mf.clint.model.Order;
import com.mf.clint.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    public String createOrder (@RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return "success";
    }
}
