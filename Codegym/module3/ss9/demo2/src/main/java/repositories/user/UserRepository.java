package repositories.user;

import models.User;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    @Override
    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM nguoi_dung WHERE email = ? AND mat_khau = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setMaNguoiDung(resultSet.getInt("ma_nguoi_dung"));
                user.setTen(resultSet.getString("ten"));
                user.setEmail(resultSet.getString("email"));
                user.setMatKhau(resultSet.getString("mat_khau"));
                user.setSoDienThoai(resultSet.getString("so_dien_thoai"));
                user.setDiaChi(resultSet.getString("dia_chi"));
                user.setMaVaiTro(resultSet.getInt("ma_vai_tro"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM nguoi_dung WHERE email = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setMaNguoiDung(resultSet.getInt("ma_nguoi_dung"));
                user.setTen(resultSet.getString("ten"));
                user.setEmail(resultSet.getString("email"));
                user.setMatKhau(resultSet.getString("mat_khau"));
                user.setSoDienThoai(resultSet.getString("so_dien_thoai"));
                user.setDiaChi(resultSet.getString("dia_chi"));
                user.setMaVaiTro(resultSet.getInt("ma_vai_tro"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(User user) {
        String sql = "INSERT INTO nguoi_dung (ten, email, mat_khau, so_dien_thoai, dia_chi, ma_vai_tro) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getTen());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMatKhau());
            preparedStatement.setString(4, user.getSoDienThoai());
            preparedStatement.setString(5, user.getDiaChi());
            preparedStatement.setInt(6, user.getMaVaiTro());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
