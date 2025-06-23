package com.example.customermanagement.repository;

import com.example.customermanagement.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerRepository implements ICustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("FROM Customer", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        if (customer.getId() == 0) {
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Customer customer = findById(id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }
}

