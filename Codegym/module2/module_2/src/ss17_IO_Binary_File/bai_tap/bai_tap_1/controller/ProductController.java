package ss17_IO_Binary_File.bai_tap.bai_tap_1.controller;

import ss17_IO_Binary_File.bai_tap.bai_tap_1.service.IProductService;
import ss17_IO_Binary_File.bai_tap.bai_tap_1.service.ProductService;

import java.util.Scanner;

public class ProductController {
    private final IProductService productService = new ProductService();
    private final Scanner scanner = new Scanner(System.in);

    public void Run() {
        int choice = -1;
        do {
            try {
                System.out.println("\n=== Product Management ===");
                System.out.println("1. Add product");
                System.out.println("2. Display all products");
                System.out.println("3. Search product by name");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Cannot be empty!");
                    continue;
                }

                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        productService.addProduct();
                        break;
                    case 2:
                        productService.displayProduct();
                        break;
                    case 3:
                        productService.searchByName();
                        break;
                    case 0:
                        System.out.println("Good bye!");
                        break;
                    default:
                        System.out.println("Error!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error!");
            }
        } while (choice != 0);
    }
}
