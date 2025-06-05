package repository.product;

import model.Product;
import utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {

    private static final String FIND_BY_NAME_CATEGORY_PAGING =
            "SELECT * FROM products WHERE name LIKE ? AND (? = 0 OR category_id = ?) LIMIT ? OFFSET ?";
    private static final String COUNT_BY_NAME_CATEGORY =
            "SELECT COUNT(*) FROM products WHERE name LIKE ? AND (? = 0 OR category_id = ?)";
    private static final String FIND_BY_ID =
            "SELECT * FROM products WHERE id = ?";
    private static final String INSERT_PRODUCT =
            "INSERT INTO products (name, price, description, manufacturer, category_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT =
            "UPDATE products SET name = ?, price = ?, description = ?, manufacturer = ?, category_id = ? WHERE id = ?";
    private static final String DELETE_PRODUCT =
            "DELETE FROM products WHERE id = ?";

    @Override
    public List<Product> findAll(int page, int pageSize) {
        return findByNameAndCategory("", 0, page, pageSize);
    }

    @Override
    public List<Product> findByNameAndCategory(String name, int categoryId, int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_NAME_CATEGORY_PAGING)) {
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, categoryId);
            ps.setInt(3, categoryId);
            ps.setInt(4, pageSize);
            ps.setInt(5, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("description"));
                p.setManufacturer(rs.getString("manufacturer"));
                p.setCategoryId(rs.getInt("category_id"));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int countByNameAndCategory(String name, int categoryId) {
        int count = 0;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(COUNT_BY_NAME_CATEGORY)) {
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, categoryId);
            ps.setInt(3, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setManufacturer(rs.getString("manufacturer"));
                product.setCategoryId(rs.getInt("category_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void save(Product product) {
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_PRODUCT)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getManufacturer());
            ps.setInt(5, product.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_PRODUCT)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getManufacturer());
            ps.setInt(5, product.getCategoryId());
            ps.setInt(6, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_PRODUCT)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
