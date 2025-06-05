package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class JDBCUtil {
    private static String url;
    private static String username;
    private static String password;
    private static String driver;

    static {
        try (InputStream input = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Không tìm thấy file db.properties trong classpath");
            }
            prop.load(input);

            url = prop.getProperty("db.url");
            username = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
            driver = prop.getProperty("db.driver");

            Class.forName(driver); // Đăng ký driver JDBC
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi đọc file db.properties hoặc load driver");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
