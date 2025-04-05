package com.example.springbootkafka.customer.models;

public record Customer(
        Long id,
        String firstName,
        String lastName,
        String address,
        String phoneNumber,
        String email) {
}
