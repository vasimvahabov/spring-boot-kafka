package com.example.springbootkafka.order.services.impl;

import com.example.springbootkafka.customer.service.CustomerService;
import com.example.springbootkafka.order.dtos.OrderRequest;
import com.example.springbootkafka.order.dtos.OrderResponse;
import com.example.springbootkafka.order.mappers.OrderMapper;
import com.example.springbootkafka.order.models.enums.OrderStatus;
import com.example.springbootkafka.order.services.OrderService;
import com.example.springbootkafka.order.models.Order;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {

    CustomerService customerService;
    OrderMapper orderMapper;
    KafkaTemplate<String, Object> kafkaTemplate;
    String topic;
    AtomicLong orderCounter;
    List<Order> ORDERS;

    public OrderServiceImpl(KafkaTemplate<String, Object> kafkaTemplate,
                            CustomerService customerService,
                            OrderMapper orderMapper,
                            @Value("${kafka.topics.order-status-updates}") String topic) {
        this.customerService = customerService;
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        this.orderMapper = orderMapper;

        this.orderCounter = new AtomicLong(0);
        this.ORDERS = new ArrayList<>();

    }

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        var customer = customerService.findCustomerById(request.customer());
        var order = Order.builder().id(orderCounter.addAndGet(1)).customer(customer).build();
        ORDERS.add(order);
        log.info("Order created: {}", order);
        return orderMapper.modelToResponse(order);
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus orderStatus) {
        var order = findOrderById(id);
        order.setOrderStatus(orderStatus);
        kafkaTemplate.send(topic, "order-status-updates", orderMapper.modelToResponse(order));
        log.info("Order status updated: {}", order);
    }

    @Override
    public Order findOrderById(Long id) {
        return ORDERS.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Order not found with %d id", id))
                );
    }

}
