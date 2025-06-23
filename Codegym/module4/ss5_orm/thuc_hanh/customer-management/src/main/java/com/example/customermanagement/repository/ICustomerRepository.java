package com.example.customermanagement.repository;

import com.example.customermanagement.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer);
    void deleteById(int id);
}
