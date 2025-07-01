package com.example.blog.repositories;

import com.example.blog.models.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findCategoryById(Long categoryId, Pageable pageable);

    Page<Blog> findAll(Pageable pageable);

    @Query("SELECT b FROM Blog b WHERE b.title LIKE %:title%")
    Page<Blog> searchBlogByTitle(@Param("title") String title, Pageable pageable);
}
