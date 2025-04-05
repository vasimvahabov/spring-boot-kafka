package com.example.springbootkafka.order.dtos;

import com.example.springbootkafka.order.models.enums.OrderStatus;
import java.time.LocalDateTime;

public record OrderResponse (
        Long id,
        Long customerId,
        String customerFirstName,
        String customerLastName,
        OrderStatus orderStatus,
        LocalDateTime orderDateTime) {
}
