package com.example.book_rental.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BorrowCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @ManyToOne
    private Book book;
}
