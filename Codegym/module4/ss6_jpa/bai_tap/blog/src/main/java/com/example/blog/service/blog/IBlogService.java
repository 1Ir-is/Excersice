package com.example.blog.service.blog;

import com.example.blog.model.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll();
    Blog findById(Long id);
    void save(Blog blog);
    void deleteById(Long id);
    List<Blog> findByCategoryId(Long categoryId);
}