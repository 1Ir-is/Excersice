package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    IBlogService blogService;

    @GetMapping
    public String showBlogList(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        return "blog/list";
    }

    @GetMapping("/add")
    public String showCreateBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @PostMapping("/add")
    public String createBlogForm(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/edit/{id}")
    public String showEditBlogForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "blog/update";
    }

    @PostMapping("/edit")
    public String editBlogForm(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/view/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "blog/detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteById(id);
        return "redirect:/blogs";
    }
}
