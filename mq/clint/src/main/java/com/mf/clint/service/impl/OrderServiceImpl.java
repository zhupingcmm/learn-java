package com.mf.clint.service.impl;

import com.mf.clint.dto.OrderDto;
import com.mf.clint.mapper.OrderMapper;
import com.mf.clint.model.Order;
import com.mf.clint.service.OrderService;
import com.mf.clint.util.ObjectTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    @Override
    public void createOrder(OrderDto orderDto) {
        orderMapper.createOrder(ObjectTransformer.transform(orderDto, Order.class));
//        return ;
    }
}
