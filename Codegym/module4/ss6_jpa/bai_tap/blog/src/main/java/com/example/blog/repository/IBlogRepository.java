package com.example.blog.repository;

import com.example.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByCategoryId(Long categoryId);
}