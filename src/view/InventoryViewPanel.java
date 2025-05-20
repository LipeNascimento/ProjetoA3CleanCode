package view;

import controller.InventoryController;
import model.Inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InventoryViewPanel extends JPanel {
    private JTextField itemNameField, quantityField, unitField;
    private JTable table;
    private DefaultTableModel tableModel;
    private InventoryController inventoryController;

    public InventoryViewPanel(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        itemNameField = new JTextField();
        quantityField = new JTextField();
        unitField = new JTextField();
        JButton saveButton = new JButton("Salvar Item");

        formPanel.add(new JLabel("Item:"));
        formPanel.add(itemNameField);
        formPanel.add(new JLabel("Quantidade:"));
        formPanel.add(quantityField);
        formPanel.add(new JLabel("Unidade:"));
        formPanel.add(unitField);
        formPanel.add(new JLabel(""));
        formPanel.add(saveButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Item", "Quantidade", "Unidade"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        saveButton.addActionListener(e -> {
            String itemName = itemNameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            String unit = unitField.getText();

            inventoryController.addInventory(itemName, quantity, unit);
            updateTable();
        });

        updateTable();
    }

    private void updateTable() {
        List<Inventory> items = inventoryController.getAllInventory();
        tableModel.setRowCount(0);
        for (Inventory i : items) {
            tableModel.addRow(new Object[]{i.getId(), i.getItemName(), i.getQuantity(), i.getUnit()});
        }
    }
}