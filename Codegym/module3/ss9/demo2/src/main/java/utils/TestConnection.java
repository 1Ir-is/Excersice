package utils;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = JDBCUtil.getConnection()) {
            System.out.println("✔ Connection successful: " + conn);
        } catch (Exception e) {
            System.err.println("❌ Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}