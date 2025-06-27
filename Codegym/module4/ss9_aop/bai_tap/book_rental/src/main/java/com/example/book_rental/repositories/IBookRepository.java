package com.example.book_rental.repositories;

import com.example.book_rental.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);

    Page<Book> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
