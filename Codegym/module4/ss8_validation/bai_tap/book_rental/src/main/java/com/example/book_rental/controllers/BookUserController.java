package com.example.book_rental.controllers;

import com.example.book_rental.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookUserController {
    @Autowired
    private IBookService bookService;

    @GetMapping("/")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "user/book-list";
    }

    @GetMapping("/detail/{id}")
    public String viewBookDetail(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("book", bookService.findById(id));
            model.addAttribute("bookId", id);
            return "user/book-detail";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "user/error";
        }
    }

    @GetMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Model model) {
        String borrowCode = bookService.borrowBook(id);
        model.addAttribute("message", "Borrowed successfully! Your code: " + borrowCode);
        return "user/borrow-result";
    }

    @GetMapping("/return")
    public String showReturnForm() {
        return "user/return-form";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String borrowCode, Model model) {
        try {
            bookService.returnBook(borrowCode);
            model.addAttribute("message", "Return successful!");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "user/return-result";
    }

    @GetMapping("/history")
    public String viewBorrowHistory(Model model) {
        model.addAttribute("borrowHistory", bookService.getBorrowHistory());
        return "user/borrow-history";
    }

    @GetMapping("/return-form/{code}")
    public String showReturnFormWithCode(@PathVariable String code, Model model) {
        model.addAttribute("code", code);
        return "user/return-form";
    }
}