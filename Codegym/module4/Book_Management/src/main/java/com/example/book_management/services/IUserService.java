package com.example.book_management.services;

public interface IUserService {
    void registerUser(String username, String rawPassword, String email);
}
