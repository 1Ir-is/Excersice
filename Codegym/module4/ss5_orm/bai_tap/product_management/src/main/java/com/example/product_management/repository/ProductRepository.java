package com.example.product_management.repository;

import com.example.product_management.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        String query = "FROM Product p ORDER BY p.id DESC";
        return entityManager.createQuery(query, Product.class).getResultList();
    }

    @Override
    public Product findById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    @Transactional
    public void save(Product product) {
        if (product.getId() == 0) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        Product product = findById(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    @Override
    public List<Product> searchByName(String name) {
        String query = "FROM Product p WHERE p.name LIKE :name ORDER BY p.id DESC";
        TypedQuery<Product> productTypedQuery = entityManager.createQuery(query, Product.class);
        productTypedQuery.setParameter("name", "%" + name + "%");
        return productTypedQuery.getResultList();
    }


    @Override
    public List<Product> findPage(int offset, int limit) {
        String query = "FROM Product p ORDER BY p.id DESC";
        return entityManager.createQuery(query, Product.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(p.id) FROM Product p", Long.class)
                .getSingleResult();
    }

}
