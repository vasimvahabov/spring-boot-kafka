package com.example.springbootkafka.order.controllers;

import com.example.springbootkafka.order.dtos.OrderRequest;
import com.example.springbootkafka.order.dtos.OrderResponse;
import com.example.springbootkafka.order.models.enums.OrderStatus;
import com.example.springbootkafka.order.services.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController("/api/v1/order")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderResponse> addOrderStatus(@RequestBody OrderRequest request) {
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable @NonNull Long id,
                                                  @RequestParam @NonNull OrderStatus orderStatus) {
        orderService.updateOrderStatus(id, orderStatus);
        return ResponseEntity.noContent().build();
    }

}
