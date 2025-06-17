package listener;

import utils.JDBCUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Ứng dụng đang khởi động...");
        try (Connection connection = JDBCUtil.getConnection()) {
            if (connection != null) {
                System.out.println("Kết nối DB thành công khi khởi động server.");
            } else {
                System.out.println("Không thể kết nối DB khi khởi động server.");
            }
        } catch (Exception e) {
            System.out.println("Lỗi kết nối DB khi khởi động:");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Ứng dụng đang tắt...");
    }
}
