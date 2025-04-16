package bai_tap_them.phuong_tien_giao_thong.utils;

public class MenuPrinter {
    public static void printMainMenu() {
        System.out.println("========= [ CHƯƠNG TRÌNH QUẢN LÝ PHƯƠNG TIỆN GIAO THÔNG ] =========");
        System.out.println("1.\tThêm mới phương tiện ");
        System.out.println("2.\tHiển thị phương tiện ");
        System.out.println("3.\tXoá phương tiện ");
        System.out.println("4.\tThoát");
        System.out.println("===================================================================");
        System.out.print("Chọn chức năng: ");
    }

    public static void printAddNewMenu() {
        System.out.println();
        System.out.println("========= [ THÊM MỚI PHƯƠNG TIỆN ] =========");
        System.out.println("1.\tThêm xe tải ");
        System.out.println("2.\tThêm ô tô ");
        System.out.println("3.\tThêm xe máy ");
        System.out.println("4.\tQuay lại menu chính");
        System.out.println("============================================");
        System.out.print("Chọn chức năng: ");
    }

    public static void printDisplayVehicleTypeMenu() {
        System.out.println();
        System.out.println("========= [ HIỂN THỊ PHƯƠNG TIỆN ] =========");
        System.out.println("1.\tHiển thị xe tải ");
        System.out.println("2.\tHiển thị ô tô ");
        System.out.println("3.\tHiển thị xe máy ");
        System.out.println("4.\tQuay lại menu chính");
        System.out.println("============================================");
        System.out.print("Chọn chức năng: ");
    }
}
