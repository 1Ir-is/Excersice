package ss17_IO_Binary_File.bai_tap.bai_tap_1.view;

import ss17_IO_Binary_File.bai_tap.bai_tap_1.controller.ProductController;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ProductController productController = new ProductController();
        productController.Run();
    }
}
