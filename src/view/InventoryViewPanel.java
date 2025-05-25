package view;

import controller.InventoryController;
import model.Inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InventoryViewPanel extends JPanel {
    private final JTextField itemNameField, quantityField, unitField;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final InventoryController inventoryController;

    public InventoryViewPanel(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        itemNameField = new JTextField();
        quantityField = new JTextField();
        unitField = new JTextField();
        JButton saveButton = new JButton("Salvar Item");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Excluir");

        formPanel.add(new JLabel("Item:"));
        formPanel.add(itemNameField);
        formPanel.add(new JLabel("Quantidade:"));
        formPanel.add(quantityField);
        formPanel.add(new JLabel("Unidade:"));
        formPanel.add(unitField);
        formPanel.add(saveButton);
        formPanel.add(editButton);
        formPanel.add(deleteButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Item", "Quantidade", "Unidade"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        saveButton.addActionListener(e -> {
            inventoryController.addInventory(
                itemNameField.getText(),
                Integer.parseInt(quantityField.getText()),
                unitField.getText()
            );
            updateTable();
        });

        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                inventoryController.updateInventory(
                    id,
                    itemNameField.getText(),
                    Integer.parseInt(quantityField.getText()),
                    unitField.getText()
                );
                updateTable();
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                inventoryController.deleteInventory(id);
                updateTable();
            }
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