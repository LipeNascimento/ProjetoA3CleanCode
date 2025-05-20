package controller;

import model.DatabaseConnection;
import model.Harvest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HarvestController {

    public void addHarvest(String cropName, int farmId, int quantity, String date) {
        String sql = "INSERT INTO harvest (crop_name, farm_id, quantity, date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cropName);
            stmt.setInt(2, farmId);
            stmt.setInt(3, quantity);
            stmt.setString(4, date);
            stmt.executeUpdate();
            System.out.println("Colheita registrada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao registrar colheita: " + e.getMessage());
        }
    }

    public List<Harvest> getAllHarvests() {
        List<Harvest> harvests = new ArrayList<>();
        String sql = "SELECT * FROM harvest";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                harvests.add(new Harvest(
                        rs.getInt("id"),
                        rs.getString("crop_name"),
                        rs.getInt("farm_id"),
                        rs.getInt("quantity"),
                        rs.getString("date")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar colheitas: " + e.getMessage());
        }
        return harvests;
    }
}