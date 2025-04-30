
package controller;

import model.Harvest;

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryController {

    private HarvestController harvestController;
    private Map<String, Double> inventory = new HashMap<>();
    private Map<String, Double> productValues = new HashMap<>();

    public InventoryController(HarvestController harvestController) {
        this.harvestController = harvestController;
    }

    public void addProduct(String plantingType, double quantity) {
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "A quantidade deve ser maior que zero.");
            return;
        }

        List<Harvest> harvests = harvestController.getHarvests();
        Harvest harvest = null;

        for (Harvest h : harvests) {
            if (h.getPlantingType().equalsIgnoreCase(plantingType)) {
                harvest = h;
                break;
            }
        }

        if (harvest == null) {
            JOptionPane.showMessageDialog(null, "Não há colheita registrada para o tipo: " + plantingType);
            return;
        }

        if (harvest.getRemainingQuantity() < quantity) {
            JOptionPane.showMessageDialog(null, "Quantidade insuficiente na colheita.\nDisponível: " + harvest.getRemainingQuantity() + " kg");
            return;
        }

        harvest.reduceQuantity(quantity);
        inventory.put(plantingType, inventory.getOrDefault(plantingType, 0.0) + quantity);
        productValues.put(plantingType, harvest.getUnitPrice());
    }

    public void removeProduct(String plantingType, double quantity) {
        if (!inventory.containsKey(plantingType)) {
            JOptionPane.showMessageDialog(null, "Produto não existe no estoque.");
            return;
        }

        double current = inventory.get(plantingType);
        if (quantity > current) {
            JOptionPane.showMessageDialog(null, "Quantidade insuficiente no estoque.\nDisponível: " + current + " kg");
            return;
        }

        inventory.put(plantingType, current - quantity);
    }

    public Map<String, Double> getInventory() {
        return inventory;
    }
}
