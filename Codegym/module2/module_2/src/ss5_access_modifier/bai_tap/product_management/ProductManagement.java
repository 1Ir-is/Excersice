package ss5_access_modifier.bai_tap.product_management;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ProductManagement {
    private static final int MAX_PRODUCT = 100;
    private static Product[] products = new Product[MAX_PRODUCT];

    static {
        products[0] = new Product("Samsung Galaxy Note 10", 10000000);
        products[1] = new Product("IPhone 12", 15300000);
        products[2] = new Product("Oppo A30", 12500000);
    }

    public void displayProduct() {
        boolean hasProduct = false;
        System.out.printf("%-5s %-25s %-10s\n", "Id", "Tên", "Gía");
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.println(products[i]);
                hasProduct = true;
            }
        }

        if (!hasProduct) {
            System.out.println("Hiện đang không có sản phẩm nào!");
        }
    }

    public void addProduct(Scanner scanner) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                System.out.print("Nhập tên sản phẩm: ");
                String name = scanner.nextLine();
                System.out.print("Nhập gía của sản phẩm: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                products[i] = new Product(name, price);
                System.out.println("Thêm sản phẩm mới thành công!");
                return;
            }
        }

        System.out.println("Không thể thêm sản phẩm! Danh sách đã đầy.");
    }

    public void updateProduct(Scanner scanner) {
        while (true) {
            System.out.print("Nhập id sản phẩm: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Product product = findProductById(id);
            if (product != null) {
                System.out.print("Nhập tên mới cho sản phẩm: ");
                String name = scanner.nextLine();
                product.setName(name);
                System.out.print("Nhập giá mới: ");
                double price = scanner.nextDouble();
                product.setPrice(price);
                scanner.nextLine();
                System.out.println("Cập nhập thông tin thành công!");
                break;
            } else {
                System.out.println("Sản phẩm không tồn tại!");
            }
        }
    }

    public void deleteProduct(Scanner scanner) {
        int id;
        int index;

        while (true) {
            System.out.print("Nhập id sản phẩm cần xoá: ");
            id = scanner.nextInt();
            scanner.nextLine();

            index = findProductIndexById(id);
            if (index != -1) {
                break;
            }
            System.out.println("Không tìm thấy sản phẩm với id này! Vui lòng nhập lại.");
        }

        for (int i = index; i < products.length - 1; i++) {
            products[i] = products[i + 1];
        }
        products[products.length - 1] = null;

        System.out.println("Xoá sản phẩm với id là \"" + id + "\" thành công!");
    }

    public void searchProduct(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên sản phẩm cần tìm: ");
            String query = scanner.nextLine().toLowerCase();
            boolean found = false;
            System.out.println("Đã tìm thấy sản phẩm");
            for (int i = 0; i < products.length; i++) {
                if (products[i] != null) {
                    String name = products[i].getName().toLowerCase();
                    if (name.contains(query)) {
                        System.out.println(products[i]);
                        found = true;
                    }
                }
            }
            if (found) {
                break;
            } else {
                System.out.println("Không tìm thấy sản phẩm có tên \"" + query + "\". Vui lòng nhập lại.");

            }
        }
    }

    private Product findProductById(int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && products[i].getId() == id) {
                return products[i];
            }
        }
        return null;
    }

    private int findProductIndexById(int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && products[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
