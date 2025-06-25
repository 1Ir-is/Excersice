package com.example.blog.controllers;

import com.example.blog.models.Blog;
import com.example.blog.models.Category;
import com.example.blog.services.blog.IBlogService;
import com.example.blog.services.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String showBlogList(@RequestParam(required = false) Long category,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "6") int size,
                               @RequestParam(required = false) String search,
                               @RequestParam(defaultValue = "desc") String sort,
                               Model model) {
        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction, "createdTime")
        );
        Page<Blog> blogPage;

        if (search != null && !search.isEmpty()) {
            blogPage = blogService.searchByTitle(search, pageable);
        } else if (category != null) {
            blogPage = blogService.findCategoryById(category, pageable);
        } else {
            blogPage = blogService.findAll(pageable);
        }

        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogPage.getTotalPages());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("category", category);
        model.addAttribute("search", search);
        model.addAttribute("sort", sort);
        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreateBlogForm(Model model) {
        Blog blog = new Blog();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("blog", blog);
        model.addAttribute("categories", categories);
        return "/blog/create";
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/update/{id}")
    public String showUpdateBlogForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid blog id: " + id)
        );
        List<Category> categories = categoryService.findAll();
        model.addAttribute("blog", blog);
        model.addAttribute("categories", categories);
        return "/blog/update";
    }

    @PostMapping("/update")
    public String updateBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/view/{id}")
    public String viewBlogDetail(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid blog id: " + id)
        );
        model.addAttribute("blog", blog);
        return "/blog/detail";
    }

    @GetMapping("delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteById(id);
        return "redirect:/blogs";
    }
}

