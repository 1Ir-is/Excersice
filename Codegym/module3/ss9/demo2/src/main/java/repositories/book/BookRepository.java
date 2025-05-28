package repositories.book;

import models.Book;
import utils.JDBCUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM sach";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setMaSach(resultSet.getInt("ma_sach"));
                book.setTenSach(resultSet.getString("ten_sach"));
                book.setTacGia(resultSet.getString("tac_gia"));
                book.setNhaXuatBan(resultSet.getString("nha_xuat_ban"));
                book.setGia(resultSet.getDouble("gia"));
                book.setMoTa(resultSet.getString("mo_ta"));
                book.setMaDanhMuc(resultSet.getInt("ma_danh_muc"));
                book.setImgUrl(resultSet.getString("img_url"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book findById(int maSach) {
        String sql = "SELECT * FROM sach WHERE ma_sach=?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, maSach);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setMaSach(resultSet.getInt("ma_sach"));
                book.setTenSach(resultSet.getString("ten_sach"));
                book.setTacGia(resultSet.getString("tac_gia"));
                book.setNhaXuatBan(resultSet.getString("nha_xuat_ban"));
                book.setGia(resultSet.getDouble("gia"));
                book.setMoTa(resultSet.getString("mo_ta"));
                book.setMaDanhMuc(resultSet.getInt("ma_danh_muc"));
                book.setImgUrl(resultSet.getString("img_url"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Book book) {
        String sql = "INSERT INTO sach (ten_sach, tac_gia, nha_xuat_ban, gia, mo_ta, ma_danh_muc, img_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTenSach());
            preparedStatement.setString(2, book.getTacGia());
            preparedStatement.setString(3, book.getNhaXuatBan());
            preparedStatement.setDouble(4, book.getGia());
            preparedStatement.setString(5, book.getMoTa());
            preparedStatement.setInt(6, book.getMaDanhMuc());
            preparedStatement.setString(7, book.getImgUrl());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Book book) {
        String sql = "UPDATE sach SET ten_sach=?, tac_gia=?, nha_xuat_ban=?, gia=?, mo_ta=?, ma_danh_muc=?, img_url=? WHERE ma_sach=?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTenSach());
            preparedStatement.setString(2, book.getTacGia());
            preparedStatement.setString(3, book.getNhaXuatBan());
            preparedStatement.setDouble(4, book.getGia());
            preparedStatement.setString(5, book.getMoTa());
            preparedStatement.setInt(6, book.getMaDanhMuc());
            preparedStatement.setString(7, book.getImgUrl());
            preparedStatement.setInt(8, book.getMaSach());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int maSach) {
        String sql = "DELETE FROM sach WHERE ma_sach=?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, maSach);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
