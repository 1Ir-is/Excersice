package com.example.bai_tap_heo.repositories.pig;

import com.example.bai_tap_heo.models.Origin;
import com.example.bai_tap_heo.models.Pig;
import com.example.bai_tap_heo.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PigRepository implements IPigRepository {
    private static final Logger LOGGER = Logger.getLogger(PigRepository.class.getName());
    private Connection connection;

    public PigRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Pig> findAll() {
        List<Pig> pigs = new ArrayList<>();
        String query = "SELECT p.*, o.name AS origin_name FROM Pig p JOIN Origin o ON p.origin_id = o.id";
        try (Connection connection = JDBCUtils.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Pig pig = mapResultSetToPig(rs);
                pig.setOrigin(new Origin(rs.getInt("origin_id"), rs.getString("origin_name"))); // Set origin with name
                pigs.add(pig);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching all pigs", e);
        }
        return pigs;
    }

    @Override
    public List<Pig> findByStatus(boolean sold) {
        List<Pig> pigs = new ArrayList<>();
        String query = "SELECT * FROM Pig WHERE sold = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, sold);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pig pig = mapResultSetToPig(rs);
                    pigs.add(pig);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching pigs by status: " + sold, e);
        }
        return pigs;
    }

    @Override
    public List<Pig> findByOrigin(int originId) {
        List<Pig> pigs = new ArrayList<>();
        String query = "SELECT * FROM Pig WHERE origin_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, originId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pig pig = mapResultSetToPig(rs);
                    pigs.add(pig);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching pigs by origin ID: " + originId, e);
        }
        return pigs;
    }

    @Override
    public Pig findById(int id) {
        String query = "SELECT p.*, o.name AS origin_name FROM Pig p JOIN Origin o ON p.origin_id = o.id WHERE p.id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Pig pig = mapResultSetToPig(rs);
                    pig.setOrigin(new Origin(rs.getInt("origin_id"), rs.getString("origin_name"))); // <-- Thêm dòng này
                    return pig;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching pig by ID: " + id, e);
        }
        return null;
    }


    @Override
    public void save(Pig pig) {
        String query = "INSERT INTO Pig (pid_number, entry_date, entry_weight, exit_date, exit_weight, origin_id, sold) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pig.getPidNumber());
            stmt.setDate(2, Date.valueOf(pig.getEntryDate()));
            stmt.setDouble(3, pig.getEntryWeight());
            stmt.setDate(4, pig.getExitDate() != null ? Date.valueOf(pig.getExitDate()) : null);
            stmt.setDouble(5, pig.getExitWeight());
            stmt.setInt(6, pig.getOrigin().getId());
            stmt.setBoolean(7, pig.isSold());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving pig: " + pig, e);
        }
    }

    @Override
    public void update(Pig pig) {
        String query = "UPDATE Pig SET pid_number = ?, entry_date = ?, entry_weight = ?, exit_date = ?, exit_weight = ?, origin_id = ?, sold = ? WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pig.getPidNumber());
            stmt.setDate(2, Date.valueOf(pig.getEntryDate()));
            stmt.setDouble(3, pig.getEntryWeight());
            stmt.setDate(4, pig.getExitDate() != null ? Date.valueOf(pig.getExitDate()) : null);
            stmt.setDouble(5, pig.getExitWeight());
            stmt.setInt(6, pig.getOrigin().getId());
            stmt.setBoolean(7, pig.isSold());
            stmt.setInt(8, pig.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating pig: " + pig, e);
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Pig WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting pig by ID: " + id, e);
        }
    }

    private Pig mapResultSetToPig(ResultSet rs) throws SQLException {
        Pig pig = new Pig();
        pig.setId(rs.getInt("id"));
        pig.setPidNumber(rs.getString("pid_number"));
        pig.setEntryDate(rs.getDate("entry_date").toLocalDate());
        pig.setEntryWeight(rs.getDouble("entry_weight"));
        pig.setExitDate(rs.getDate("exit_date") != null ? rs.getDate("exit_date").toLocalDate() : null);
        pig.setExitWeight(rs.getDouble("exit_weight"));
        pig.setSold(rs.getBoolean("sold"));
        return pig;
    }

    @Override
    public List<Origin> findAllOrigins() {
        List<Origin> origins = new ArrayList<>();
        String query = "SELECT * FROM Origin";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Origin origin = new Origin(rs.getInt("id"), rs.getString("name"));
                origins.add(origin);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching all origins", e);
        }
        return origins;
    }

    @Override
    public List<Pig> searchPigs(Boolean sold, String pidNumber, Integer originId) {
        List<Pig> pigs = new ArrayList<>();
        StringBuilder query = new StringBuilder(
                "SELECT p.*, o.name AS origin_name FROM Pig p JOIN Origin o ON p.origin_id = o.id WHERE 1=1"
        );

        List<Object> parameters = new ArrayList<>();

        if (sold != null) {
            query.append(" AND p.sold = ?");
            parameters.add(sold);
        }

        if (pidNumber != null && !pidNumber.trim().isEmpty()) {
            query.append(" AND p.pid_number LIKE ?");
            parameters.add("%" + pidNumber.trim() + "%");
        }

        if (originId != null) {
            query.append(" AND p.origin_id = ?");
            parameters.add(originId);
        }

        try (PreparedStatement stmt = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pig pig = mapResultSetToPig(rs);
                    pig.setOrigin(new Origin(rs.getInt("origin_id"), rs.getString("origin_name")));
                    pigs.add(pig);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching pigs with filters", e);
        }

        return pigs;
    }

}