package com.example.springbootkafka.order.services;

import com.example.springbootkafka.order.dtos.OrderRequest;
import com.example.springbootkafka.order.dtos.OrderResponse;
import com.example.springbootkafka.order.models.Order;
import com.example.springbootkafka.order.models.enums.OrderStatus;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    void updateOrderStatus(Long id, OrderStatus orderStatus);

    Order findOrderById(Long id);

}
