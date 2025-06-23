package com.example.customermanagement.service;

import com.example.customermanagement.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer);
    void deleteById(int id);
}
