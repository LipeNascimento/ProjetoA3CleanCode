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
            System.out.println("Item de estoque adicionado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar item de estoque: " + e.getMessage());
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
            System.out.println("Erro ao buscar itens de estoque: " + e.getMessage());
        }
        return items;
    }
}