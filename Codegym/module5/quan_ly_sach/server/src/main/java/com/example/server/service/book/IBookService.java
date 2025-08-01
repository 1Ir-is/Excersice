package com.example.server.service.book;

import com.example.server.dto.BookDTO;

import java.util.List;

public interface IBookService {
    BookDTO createBook(BookDTO bookDTO);

    BookDTO getBookById(Long id);

    List<BookDTO> getAllBooks();

    List<BookDTO> getBooksByCategory(Long categoryId);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);
}
