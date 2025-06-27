package com.example.book_rental.services;

import com.example.book_rental.dtos.BookDTO;
import com.example.book_rental.models.Book;
import com.example.book_rental.models.BorrowCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {
    Page<Book> findAll(Pageable pageable);

    List<BorrowCode> getBorrowHistory();

    BookDTO findById(Long id);

    void saveBook(BookDTO bookDTO);

    void updateBook(Long id, BookDTO bookDTO);

    void deleteById(Long id);

    String borrowBook(Long id);

    void returnBook(String borrowCode);

    Page<Book> searchBooks(String keyword, Pageable pageable);
}
