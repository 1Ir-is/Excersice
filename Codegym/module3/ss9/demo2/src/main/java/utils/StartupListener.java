package utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebListener
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(">>> Ung dung dang khoi dong...");

        try (Connection conn = JDBCUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // 1. them vai tro admin
            String checkAdminRoleSql = "SELECT * FROM vai_tro WHERE ma_vai_tro = 0";
            ResultSet rsAdminRole = stmt.executeQuery(checkAdminRoleSql);
            if (!rsAdminRole.next()) {
                String insertAdminRoleSql = "INSERT INTO vai_tro (ma_vai_tro, ten_vai_tro) VALUES (0, 'Admin')";
                stmt.executeUpdate(insertAdminRoleSql);
                System.out.println("Da them vai tro 'Admin'.");
            }

            // 2. them vai tro user
            String checkUserRoleSql = "SELECT * FROM vai_tro WHERE ma_vai_tro = 1";
            ResultSet rsUserRole = stmt.executeQuery(checkUserRoleSql);
            if (!rsUserRole.next()) {
                String insertUserRoleSql = "INSERT INTO vai_tro (ma_vai_tro, ten_vai_tro) VALUES (1, 'User')";
                stmt.executeUpdate(insertUserRoleSql);
                System.out.println("Da them vai tro 'User'.");
            }

            // 3. Thêm tài khoản admin nếu chưa có
            String checkAdminUserSql = "SELECT * FROM nguoi_dung WHERE email = 'admin@gmail.com'";
            ResultSet rsAdminUser = stmt.executeQuery(checkAdminUserSql);
            if (!rsAdminUser.next()) {
                String insertAdminUserSql = "INSERT INTO nguoi_dung (ten, email, mat_khau, so_dien_thoai, dia_chi, ma_vai_tro) " +
                        "VALUES ('Admin', 'admin@gmail.com', '123', '0900000000', 'Hệ thống', 0)";
                stmt.executeUpdate(insertAdminUserSql);
                System.out.println("Da them tai khoan admin.");
            }

        } catch (Exception e) {
            System.out.println("Loi khi tao du lieu:");
            e.printStackTrace();
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Không cần làm gì khi ứng dụng dừng
    }
}
