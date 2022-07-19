package com.mf.clint.mapper;

import com.mf.clint.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int createOrder(Order order);
}
