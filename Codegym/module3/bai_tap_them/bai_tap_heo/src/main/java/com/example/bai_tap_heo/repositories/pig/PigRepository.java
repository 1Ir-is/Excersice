package com.example.pigfarm.repository.impl;

import com.example.bai_tap_heo.models.Pig;
import com.example.bai_tap_heo.repositories.pig.IPigRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PigRepository implements IPigRepository {
    private Connection connection;

    public PigRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Pig> findAll() {
        List<Pig> pigs = new ArrayList<>();
        String query = "SELECT * FROM Pig";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Pig pig = mapResultSetToPig(rs);
                pigs.add(pig);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pigs;
    }

    @Override
    public List<Pig> findByStatus(boolean sold) {
        List<Pig> pigs = new ArrayList<>();
        String query = "SELECT * FROM Pig WHERE sold = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, sold);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pig pig = mapResultSetToPig(rs);
                    pigs.add(pig);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pigs;
    }

    @Override
    public List<Pig> findByOrigin(int originId) {
        List<Pig> pigs = new ArrayList<>();
        String query = "SELECT * FROM Pig WHERE origin_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, originId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pig pig = mapResultSetToPig(rs);
                    pigs.add(pig);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pigs;
    }

    @Override
    public Pig findById(int id) {
        String query = "SELECT * FROM Pig WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPig(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Pig pig) {
        String query = "INSERT INTO Pig (pid_number, entry_date, entry_weight, exit_date, exit_weight, origin_id, sold) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pig.getPidNumber());
            stmt.setDate(2, Date.valueOf(pig.getEntryDate()));
            stmt.setDouble(3, pig.getEntryWeight());
            stmt.setDate(4, pig.getExitDate() != null ? Date.valueOf(pig.getExitDate()) : null);
            stmt.setDouble(5, pig.getExitWeight());
            stmt.setInt(6, pig.getOrigin().getId());
            stmt.setBoolean(7, pig.isSold());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Pig pig) {
        String query = "UPDATE Pig SET pid_number = ?, entry_date = ?, entry_weight = ?, exit_date = ?, exit_weight = ?, origin_id = ?, sold = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Pig WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
}