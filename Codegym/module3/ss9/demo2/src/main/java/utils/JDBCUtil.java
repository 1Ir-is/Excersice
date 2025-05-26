package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/book_management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // đổi nếu dùng username khác
    private static final String PASSWORD = "codegym123456789"; // thay bằng mật khẩu MySQL thật

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // nạp driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
