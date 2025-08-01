package com.example.server.repository;

import com.example.server.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategoryId(Long categoryId);

    boolean existsByBookCode(String bookCode);
}