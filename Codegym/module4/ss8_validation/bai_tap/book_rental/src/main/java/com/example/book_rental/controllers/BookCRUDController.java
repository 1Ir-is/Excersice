package com.example.book_rental.controllers;

import com.example.book_rental.dtos.BookDTO;
import com.example.book_rental.models.Book;
import com.example.book_rental.services.IBookService;
import com.example.book_rental.validators.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/books")
public class BookCRUDController {
    @Autowired
    private IBookService bookService;

    private final BookValidator bookValidator = new BookValidator();

    @GetMapping()
    public String showListBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "admin/book-list";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        BookDTO bookDTO = bookService.findById(id);
        model.addAttribute("book", bookDTO);
        return "admin/book-view";
    }

    @GetMapping("/create")
    public String showCreateBookForm(Model model) {
        BookDTO bookDTO = new BookDTO();
        model.addAttribute("book", bookDTO);
        return "admin/book-create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") BookDTO bookDTO, BindingResult bindingResult, Model model) {
        bookValidator.validate(bookDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/book-create";
        }

        bookService.saveBook(bookDTO);
        return "redirect:/admin/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        BookDTO bookDTO = bookService.findById(id);
        model.addAttribute("book", bookDTO);
        model.addAttribute("bookId", id);
        return "admin/book-update";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") BookDTO bookDTO, BindingResult bindingResult, Model model) {
        bookValidator.validate(bookDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("bookId", id);
            return "admin/book-update";
        }

        bookService.updateBook(id, bookDTO);
        return "redirect:/admin/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/admin/books";
    }
}