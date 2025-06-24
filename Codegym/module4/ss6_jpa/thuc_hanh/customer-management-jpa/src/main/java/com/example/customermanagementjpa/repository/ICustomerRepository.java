package com.example.customermanagementjpa.repository;

import com.example.customermanagementjpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
