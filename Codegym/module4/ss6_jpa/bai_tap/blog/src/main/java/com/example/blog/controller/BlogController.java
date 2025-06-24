package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.service.blog.IBlogService;
import com.example.blog.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String showBlogList(@RequestParam(required = false) Long category, Model model) {
        List<Blog> blogs = (category == null) ? blogService.findAll() : blogService.findByCategoryId(category);
        model.addAttribute("blogs", blogs);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("category", category);
        return "blog/list";
    }

    @GetMapping("/add")
    public String showCreateBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "blog/create";
    }

    @PostMapping("/add")
    public String createBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs/manage";
    }

    @GetMapping("/edit/{id}")
    public String showEditBlogForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "blog/update";
    }

    @PostMapping("/edit")
    public String editBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs/manage";
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
        return "redirect:/blogs/manage";
    }

    @GetMapping("/manage")
    public String manageBlogs(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        return "blog/manage_list";
    }
}