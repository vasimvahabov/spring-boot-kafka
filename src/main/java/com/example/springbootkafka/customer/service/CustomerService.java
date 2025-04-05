package com.example.springbootkafka.customer.service;

import com.example.springbootkafka.customer.models.Customer;

public interface CustomerService {

    Customer findCustomerById(Long id);

}
