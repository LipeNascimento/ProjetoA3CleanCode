package controller;

import model.DatabaseConnection;
import model.Inventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryController {

    public void addInventory(String itemName, int quantity, String unit) {
        String sql = "INSERT INTO inventory (item_name, quantity, unit) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, itemName);
            stmt.setInt(2, quantity);
            stmt.setString(3, unit);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar item: " + e.getMessage());
        }
    }

    public List<Inventory> getAllInventory() {
        List<Inventory> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(new Inventory(
                        rs.getInt("id"),
                        rs.getString("item_name"),
                        rs.getInt("quantity"),
                        rs.getString("unit")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar itens: " + e.getMessage());
        }
        return items;
    }

    public void updateInventory(int id, String itemName, int quantity, String unit) {
        String sql = "UPDATE inventory SET item_name = ?, quantity = ?, unit = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, itemName);
            stmt.setInt(2, quantity);
            stmt.setString(3, unit);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar item: " + e.getMessage());
        }
    }

    public void deleteInventory(int id) {
        String sql = "DELETE FROM inventory WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir item: " + e.getMessage());
        }
    }
}