package com.example.springbootkafka.order.models;

import com.example.springbootkafka.customer.models.Customer;
import com.example.springbootkafka.order.models.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    Long id;

    Customer customer;

    @Setter
    @Builder.Default
    OrderStatus orderStatus = OrderStatus.PLACED;

    @Builder.Default
    LocalDateTime orderDateTime = LocalDateTime.now();

}
