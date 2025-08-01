package com.example.server.service.book;

import com.example.server.dto.BookDTO;
import com.example.server.model.Book;
import com.example.server.model.Category;
import com.example.server.repository.IBookRepository;
import com.example.server.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Service
public class BookService implements IBookService {

    private final IBookRepository bookRepository;
    private final ICategoryRepository categoryRepository;
    private static final Pattern BOOK_CODE_PATTERN = Pattern.compile("BO-\\d{4}$");

    @Autowired
    public BookService(IBookRepository bookRepository, ICategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setBookCode(book.getBookCode());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setCategoryId(book.getCategory().getId());
        bookDTO.setCategoryName(book.getCategory().getCategoryName());
        bookDTO.setImportDate(book.getImportDate());
        bookDTO.setQuantity(book.getQuantity());
        return bookDTO;
    }

    private void validateBook(BookDTO bookDTO) {
        List<String> errors = new ArrayList<>();

        if (bookDTO.getBookCode() == null || bookDTO.getBookCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Mã sách không được để trống!");
        }
        if (!BOOK_CODE_PATTERN.matcher(bookDTO.getBookCode()).matches()) {
            throw new IllegalArgumentException("Mã sách phải theo định dạng BO-XXXX");
        }

        if (bookDTO.getTitle() == null || bookDTO.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sách không được để trống!");
        }
        if (bookDTO.getTitle().length() > 100) {
            throw new IllegalArgumentException("Tên sách không được dài quá 100 ký tự");
        }

        if (bookDTO.getImportDate() == null) {
            throw new IllegalArgumentException("Ngày nhập không được để trống!");
        }
        if (bookDTO.getImportDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày nhập không được lớn hơn ngày hiện tại");
        }

        if (bookDTO.getQuantity() == null) {
            throw new IllegalArgumentException("Số lượng sách không được để trống!");
        }
        if (bookDTO.getQuantity() <= 0) {
            throw new IllegalArgumentException("Số lượng sách phải lớn hơn 0");
        }
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        validateBook(bookDTO);

        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + bookDTO.getCategoryId()));

        Book book = new Book();
        book.setBookCode(bookDTO.getBookCode());
        book.setTitle(bookDTO.getTitle());
        book.setCategory(category);
        book.setImportDate(bookDTO.getImportDate());
        book.setQuantity(bookDTO.getQuantity());

        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
        return convertToDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Book book : books) {
            bookDTOS.add(convertToDTO(book));
        }
        return bookDTOS;
    }

    @Override
    public List<BookDTO> getBooksByCategory(Long categoryId) {
        List<Book> books = bookRepository.findByCategoryId(categoryId);
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Book book : books) {
            bookDTOS.add(convertToDTO(book));
        }

        return bookDTOS;
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        validateBook(bookDTO);

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + bookDTO.getCategoryId()));

        book.setBookCode(bookDTO.getBookCode());
        book.setTitle(bookDTO.getTitle());
        book.setCategory(category);
        book.setImportDate(bookDTO.getImportDate());
        book.setQuantity(bookDTO.getQuantity());

        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NoSuchElementException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}