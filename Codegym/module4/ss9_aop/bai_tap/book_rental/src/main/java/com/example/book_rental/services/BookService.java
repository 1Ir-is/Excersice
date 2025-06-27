package com.example.book_rental.services;

import com.example.book_rental.dtos.BookDTO;
import com.example.book_rental.models.Book;
import com.example.book_rental.models.BorrowCode;
import com.example.book_rental.repositories.IBookRepository;
import com.example.book_rental.repositories.IBorrowCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IBorrowCodeRepository borrowCodeRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found!")
        );
        return convertToDTO(book);
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName(book.getName());
        bookDTO.setQuantity(book.getQuantity());
        return bookDTO;
    }

    @Override
    public void saveBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        bookRepository.save(book);
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setQuantity(bookDTO.getQuantity());
        return book;
    }

    @Override
    public void updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found!")
        );
        book.setName(bookDTO.getName());
        book.setQuantity(bookDTO.getQuantity());
        bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public String borrowBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book is not found")
        );
        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Book is not available for borrowing!");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        String borrowCode = generateBorrowCode();
        BorrowCode code = new BorrowCode();
        code.setCode(borrowCode);
        code.setBook(book);
        borrowCodeRepository.save(code);

        return borrowCode;
    }

    private String generateBorrowCode() {
        Random random = new Random();
        int code = 10000 + random.nextInt(90000);
        return String.valueOf(code);
    }

    @Override
    public void returnBook(String borrowCode) {
        BorrowCode code = borrowCodeRepository.findByCode(borrowCode).orElseThrow(
                () -> new RuntimeException("Invalid borrow code!")
        );

        Book book = code.getBook();

        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        borrowCodeRepository.delete(code);
    }

    @Override
    public Page<Book> searchBooks(String keyword, Pageable pageable) {
        return bookRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public List<BorrowCode> getBorrowHistory() {
        return borrowCodeRepository.findAll();
    }
}
