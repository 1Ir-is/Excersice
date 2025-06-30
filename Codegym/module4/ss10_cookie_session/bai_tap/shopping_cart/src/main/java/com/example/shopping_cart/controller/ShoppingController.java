package com.example.shopping_cart.controller;

import com.example.shopping_cart.model.Cart;
import com.example.shopping_cart.model.Product;
import com.example.shopping_cart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ShoppingController {

    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setUpCart() {
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart(@ModelAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }

    @PostMapping("/updateQuantity")
    public String updateCart(@RequestParam("productId") Long id, @RequestParam("quantity") int quantity, @ModelAttribute("cart") Cart cart) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            cart.updateProduct(product, quantity);
        }
        return "redirect:/shopping-cart";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, @ModelAttribute("cart") Cart cart) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            cart.removeProduct(product);
        }
        return "redirect:/shopping-cart";
    }

    @GetMapping("/cart/checkout")
    public String checkout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "checkout-success";
    }
}
