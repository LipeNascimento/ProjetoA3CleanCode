package view;

import javax.swing.*;
import java.util.Map;

public class InventoryView {

    public String getProductToAdd() {
        return JOptionPane.showInputDialog("Digite o tipo de plantio do produto a ser adicionado:");
    }

    public double getQuantityToAdd() {
        String quantityStr = JOptionPane.showInputDialog("Digite a quantidade a ser adicionada:");
        return Double.parseDouble(quantityStr);
    }

    public String getProductToRemove() {
        return JOptionPane.showInputDialog("Digite o tipo de plantio do produto a ser removido:");
    }

    public double getQuantityToRemove() {
        String quantityStr = JOptionPane.showInputDialog("Digite a quantidade a ser removida:");
        return Double.parseDouble(quantityStr);
    }

    public void displayInventory(Map<String, Double> inventory) {
        if (inventory.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O inventário está vazio.");
        } else {
            StringBuilder inventoryList = new StringBuilder("\n=========== Inventário de Produtos ===========\n");
            for (Map.Entry<String, Double> entry : inventory.entrySet()) {
                inventoryList.append(String.format("Tipo de Plantio: %s, Quantidade: %.2f%n", entry.getKey(), entry.getValue()));
            }
            inventoryList.append("----------------------------------------------");
            JOptionPane.showMessageDialog(null, inventoryList.toString());
        }
    }

    public void displayProductValues(Map<String, Double> productValues, Map<String, Double> productQuantities) {
        if (productValues.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há valores de produtos no inventário.");
        } else {
            StringBuilder productList = new StringBuilder("\n=========== Valores dos Produtos ===========\n");
            for (Map.Entry<String, Double> entry : productValues.entrySet()) {
                productList.append(String.format("Tipo de Plantio: %s, Valor Total: R$ %.2f, Quantidade Restante: %.2f%n", entry.getKey(), entry.getValue(), productQuantities.get(entry.getKey())));
            }
            productList.append("--------------------------------------------");
            JOptionPane.showMessageDialog(null, productList.toString());
        }
    }

    public void displayHarvestOptions(Map<String, Double> harvestOptions) {
        if (harvestOptions.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma colheita disponível.");
        } else {
            StringBuilder harvestList = new StringBuilder("\nOpções de colheita disponíveis:\n");
            for (Map.Entry<String, Double> entry : harvestOptions.entrySet()) {
                harvestList.append(String.format("Tipo de Plantio: %s, Quantidade Restante: %.2f%n", entry.getKey(), entry.getValue()));
            }
            JOptionPane.showMessageDialog(null, harvestList.toString());
        }
    }
}