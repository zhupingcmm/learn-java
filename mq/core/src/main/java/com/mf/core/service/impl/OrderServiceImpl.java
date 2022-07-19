package com.mf.core.service.impl;


import com.mf.core.dto.OrderDto;
import com.mf.core.mapper.OrderMapper;
import com.mf.core.model.Order;
import com.mf.core.service.OrderService;
import com.mf.core.util.ObjectTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;

    @Override
    public void consumerOrder(OrderDto orderDto) {
        orderMapper.consumerOrder(ObjectTransformer.transform(orderDto, Order.class));
    }
}
