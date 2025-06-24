package com.example.customermanagementjpa.service;

import com.example.customermanagementjpa.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
    void delete(Long id);
}
