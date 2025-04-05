package com.example.springbootkafka.customer.service.impl;

import com.example.springbootkafka.customer.service.CustomerService;
import com.example.springbootkafka.customer.models.Customer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerServiceImpl implements CustomerService {

    List<Customer> CUSTOMERS;

    public CustomerServiceImpl() {
        CUSTOMERS = new ArrayList<>(List.of(
                new Customer(1L, "John", "Doe", "123 Main St", "555-1234", "john.doe@example.com"),
                new Customer(2L, "Jane", "Smith", "456 Oak Ave", "555-5678", "jane.smith@example.com")
        ));
    }

    @Override
    public Customer findCustomerById(Long id) {
        return CUSTOMERS.stream()
                .filter(customer -> customer.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Customer not found with %d id", id))
                );
    }

}
