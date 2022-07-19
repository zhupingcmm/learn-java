package com.mf.clint.service.impl;

import com.alibaba.fastjson.JSON;
import com.mf.clint.dto.OrderDto;
import com.mf.clint.mapper.OrderMapper;
import com.mf.clint.model.Order;
import com.mf.clint.service.OrderService;
import com.mf.clint.util.ObjectTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Topic;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final JmsMessagingTemplate jmsMessagingTemplate;

    private final Topic topic;
    @Override
    public void createOrder(OrderDto orderDto) {
        jmsMessagingTemplate.convertAndSend(topic, JSON.toJSONString(ObjectTransformer.transform(orderDto, Order.class)));
        orderMapper.createOrder(ObjectTransformer.transform(orderDto, Order.class));
    }
}
