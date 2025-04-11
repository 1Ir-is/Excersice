package ss5_access_modifier.bai_tap.product_management;

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
        System.out.printf("%-5s %-25s %-10s\n", "Id", "Tên", "Giá");
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
                String name = scanner.nextLine().trim();
                while (name.isEmpty()) {
                    System.out.print("Tên sản phẩm không được để trống. Vui lòng nhập lại: ");
                    name = scanner.nextLine().trim();
                }

                System.out.print("Nhập giá của sản phẩm: ");
                double price = validatePrice(scanner);

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
            int id = validateId(scanner);
            Product product = findProductById(id);
            if (product != null) {
                System.out.print("Nhập tên mới cho sản phẩm: ");
                String name = scanner.nextLine().trim();
                while (name.isEmpty()) {
                    System.out.print("Tên sản phẩm không được để trống. Vui lòng nhập lại: ");
                    name = scanner.nextLine().trim();
                }
                product.setName(name);

                System.out.print("Nhập giá mới: ");
                double price = validatePrice(scanner);
                product.setPrice(price);

                System.out.println("Cập nhập thông tin thành công!");
                break;
            } else {
                System.out.println("Sản phẩm không tồn tại!");
            }
        }
    }

    public void deleteProduct(Scanner scanner) {
        while (true) {
            System.out.print("Nhập id sản phẩm cần xoá: ");
            int id = validateId(scanner);

            int index = findProductIndexById(id);
            if (index != -1) {
                for (int i = index; i < products.length - 1; i++) {
                    products[i] = products[i + 1];
                }
                products[products.length - 1] = null;

                System.out.println("Xoá sản phẩm với id là \"" + id + "\" thành công!");
                break;
            } else {
                System.out.println("Không tìm thấy sản phẩm với id này! Vui lòng nhập lại.");
            }
        }
    }

    public void searchProduct(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên sản phẩm cần tìm: ");
            String query = scanner.nextLine().trim().toLowerCase();
            while (query.isEmpty()) {
                System.out.print("Tên sản phẩm không được để trống. Vui lòng nhập lại: ");
                query = scanner.nextLine().trim().toLowerCase();
            }

            boolean found = false;
            System.out.println("Đã tìm thấy sản phẩm:");
            System.out.printf("%-5s %-25s %-10s\n", "Id", "Tên", "Giá");
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

    private int validateId(Scanner scanner) {
        int id;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                if (id > 0) {
                    return id;
                } else {
                    System.out.print("ID phải là số nguyên dương. Vui lòng nhập lại: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("ID không hợp lệ. Vui lòng nhập lại: ");
            }
        }
    }

    private double validatePrice(Scanner scanner) {
        double price;
        while (true) {
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
                if (price > 0) {
                    return price;
                } else {
                    System.out.print("Giá phải là số dương. Vui lòng nhập lại: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Giá không hợp lệ. Vui lòng nhập lại: ");
            }
        }
    }
}