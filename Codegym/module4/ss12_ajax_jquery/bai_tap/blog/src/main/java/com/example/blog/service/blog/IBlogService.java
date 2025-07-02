package com.example.blog.service.blog;

import com.example.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBlogService {
    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findCategoryById(Long categoryId, Pageable pageable);

    Page<Blog> searchByTitle(String title, Pageable pageable);

    Optional<Blog> findById(Long id);

    void save(Blog blog);

    void deleteById(Long id);
}
