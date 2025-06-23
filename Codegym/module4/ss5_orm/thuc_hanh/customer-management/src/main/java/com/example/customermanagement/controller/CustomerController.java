package com.example.customermanagement.controller;


import com.example.customermanagement.model.Customer;
import com.example.customermanagement.service.CustomerService;
import com.example.customermanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @GetMapping
    public String showListCustomer(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "/index";
    }

    @GetMapping("/view/{id}")
    public String viewCustomer(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/view";
    }

    @GetMapping("/create")
    public String showCreateCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "/create";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditCustomerForm(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/update";
    }

    @PostMapping("/edit")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }
}
