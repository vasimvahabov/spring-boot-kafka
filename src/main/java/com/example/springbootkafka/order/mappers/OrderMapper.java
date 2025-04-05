package com.example.springbootkafka.order.mappers;

import com.example.springbootkafka.order.dtos.OrderResponse;
import com.example.springbootkafka.order.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "customerId", source = "customer.id"),
            @Mapping(target = "customerFirstName", source = "customer.firstName"),
            @Mapping(target = "customerLastName", source = "customer.lastName")
    })
    OrderResponse modelToResponse(Order order);

}
