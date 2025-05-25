package controller;

import model.Harvest;

import java.util.HashMap;
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
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        Harvest harvest = harvestController.getHarvestByType(plantingType);
        if (harvest == null || harvest.getRemainingQuantity() < quantity) {
            throw new IllegalArgumentException("Quantidade insuficiente na colheita.");
        }

        inventory.put(plantingType, inventory.getOrDefault(plantingType, 0.0) + quantity);
        productValues.put(plantingType, productValues.getOrDefault(plantingType, 0.0) + (quantity * harvest.getUnitPrice()));
        harvestController.reduceQuantity(plantingType, quantity);
    }

    public void removeProduct(String plantingType, double quantity) {
        if (!inventory.containsKey(plantingType) || inventory.get(plantingType) < quantity) {
            throw new IllegalArgumentException("Quantidade insuficiente no inventário.");
        }

        inventory.put(plantingType, inventory.get(plantingType) - quantity);
        if (inventory.get(plantingType) <= 0) {
            inventory.remove(plantingType);
        }
    }

    public Map<String, Double> getProducts() {
        return inventory;
    }

    public Map<String, Double> getProductValues() {
        return productValues;
    }

    public Map<String, Double> getProductQuantities() {
        return inventory;
    }
}