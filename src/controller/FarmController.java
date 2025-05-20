package controller;

import model.DatabaseConnection;
import model.Farm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmController {

    public void addFarm(String name, String location, String cropType, double area) {
        String sql = "INSERT INTO farm (name, location) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, location);
            stmt.executeUpdate();
            System.out.println("Fazenda inserida com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir fazenda: " + e.getMessage());
        }
    }

    public List<Farm> getAllFarms() {
        List<Farm> farms = new ArrayList<>();
        String sql = "SELECT * FROM farm";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                farms.add(new Farm(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar fazendas: " + e.getMessage());
        }
        return farms;
    }

    public Farm getFarmById(int id) {
        String sql = "SELECT * FROM farm WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Farm(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar fazenda: " + e.getMessage());
        }
        return null;
    }

    public void updateFarm(int id, String name, String location) {
        String sql = "UPDATE farm SET name = ?, location = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, location);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Fazenda atualizada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar fazenda: " + e.getMessage());
        }
    }

    public void deleteFarm(int id) {
        String sql = "DELETE FROM farm WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Fazenda deletada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar fazenda: " + e.getMessage());
        }
    }
}