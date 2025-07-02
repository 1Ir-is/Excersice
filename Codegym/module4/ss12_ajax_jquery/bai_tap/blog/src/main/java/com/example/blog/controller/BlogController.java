package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.service.blog.IBlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Operation(summary = "Get all blogs", description = "Retrieve a paginated list of all blogs")
    @GetMapping()
    public ResponseEntity<Page<Blog>> getAllBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Blog> blogs = blogService.findAll(pageable);
        return blogs.isEmpty() ? new ResponseEntity<>(blogs, HttpStatus.NO_CONTENT) : new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @Operation(summary = "Get blogs by category", description = "Retrieve blogs by category ID")
    @GetMapping("/category/{id}")
    public ResponseEntity<Page<Blog>> getBlogByCategory(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Blog> blogs = blogService.findCategoryById(id, pageable);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @Operation(summary = "Get blog by ID", description = "Retrieve a blog by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        return blog.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a new blog", description = "Add a new blog to the system")
    @PostMapping()
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        blogService.save(blog);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a blog", description = "Update an existing blog by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        Optional<Blog> existingBlog = blogService.findById(id);
        if (existingBlog.isPresent()) {
            blog.setId(id);
            blogService.save(blog);
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete a blog", description = "Delete a blog by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            blogService.deleteById(id);
            return new ResponseEntity<>(blogOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Search blogs by title", description = "Search blogs by their title")
    @GetMapping("/search")
    public ResponseEntity<Page<Blog>> searchBlogByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Blog> blogs = blogService.searchByTitle(title, pageable);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
}