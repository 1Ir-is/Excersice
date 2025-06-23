    package com.example.product_management.controller;

    import com.example.product_management.model.Product;
    import com.example.product_management.service.IProductService;
    import com.example.product_management.service.ProductService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    import java.util.List;

    @Controller
    @RequestMapping("/products")
    public class ProductController {

        @Autowired
        private IProductService productService;

        @GetMapping
        public String showListProducts(
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "5") int size,
                Model model) {

            List<Product> products = productService.findPage(page, size);
            long totalItems = productService.count();
            int totalPages = (int) Math.ceil((double) totalItems / size);

            model.addAttribute("products", products);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("pageSize", size);
            return "/list";
        }

        @GetMapping("/create")
        public String showCreateProductForm(Model model) {
            model.addAttribute("product", new Product());
            return "/create";
        }

        @PostMapping("/create")
        public String createProduct(@ModelAttribute Product product, RedirectAttributes redirect) {
            productService.save(product);
            redirect.addFlashAttribute("success", "Created successfully!");
            return "redirect:/products";
        }

        @GetMapping("/edit/{id}")
        public String showEditProductForm(@PathVariable int id, Model model) {
            model.addAttribute("product", productService.findById(id));
            return "/edit";
        }

        @PostMapping("/edit")
        public String editProductForm(@ModelAttribute Product product, RedirectAttributes redirect) {
            productService.save(product);
            redirect.addFlashAttribute("success", "Updated successfully!");
            return "redirect:/products";
        }

        @PostMapping("/delete")
        public String delete(@RequestParam int id, RedirectAttributes redirect) {
            productService.delete(id);
            redirect.addFlashAttribute("success", "Deleted successfully!");
            return "redirect:/products";
        }

        @GetMapping("/view/{id}")
        public String viewProduct(@PathVariable int id, Model model) {
            model.addAttribute("product", productService.findById(id));
            return "/view";
        }

        @GetMapping("/search")
        public String searchProductByName(
                @RequestParam String name,
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "5") int size,
                Model model) {

            List<Product> products = productService.searchByName(name);

            long totalItems = products.size();
            int totalPages = (int) Math.ceil((double) totalItems / size);
            int fromIndex = (page - 1) * size;
            int toIndex = Math.min(fromIndex + size, products.size());

            List<Product> paginated = products.subList(fromIndex, toIndex);

            model.addAttribute("products", paginated);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("pageSize", size);
            model.addAttribute("searchName", name);

            return "/list";
        }



    }
