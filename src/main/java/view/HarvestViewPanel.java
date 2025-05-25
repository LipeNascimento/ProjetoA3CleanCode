package view;

import controller.HarvestController;
import model.Harvest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HarvestViewPanel extends JPanel {
    private final JTextField cropNameField, farmIdField, quantityField, dateField;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final HarvestController harvestController;

    public HarvestViewPanel(HarvestController harvestController) {
        this.harvestController = harvestController;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        cropNameField = new JTextField();
        farmIdField = new JTextField();
        quantityField = new JTextField();
        dateField = new JTextField();
        JButton saveButton = new JButton("Salvar Colheita");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Excluir");

        formPanel.add(new JLabel("Tipo de Plantio:"));
        formPanel.add(cropNameField);
        formPanel.add(new JLabel("ID da Fazenda:"));
        formPanel.add(farmIdField);
        formPanel.add(new JLabel("Quantidade:"));
        formPanel.add(quantityField);
        formPanel.add(new JLabel("Data:"));
        formPanel.add(dateField);
        formPanel.add(saveButton);
        formPanel.add(editButton);
        formPanel.add(deleteButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Plantio", "Fazenda", "Quantidade", "Data"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        saveButton.addActionListener(e -> {
            harvestController.addHarvest(
                cropNameField.getText(),
                Integer.parseInt(farmIdField.getText()),
                Integer.parseInt(quantityField.getText()),
                dateField.getText()
            );
            updateTable();
        });

        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                harvestController.updateHarvest(
                    id,
                    cropNameField.getText(),
                    Integer.parseInt(farmIdField.getText()),
                    Integer.parseInt(quantityField.getText()),
                    dateField.getText()
                );
                updateTable();
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                harvestController.deleteHarvest(id);
                updateTable();
            }
        });

        updateTable();
    }

    private void updateTable() {
        List<Harvest> harvests = harvestController.getAllHarvests();
        tableModel.setRowCount(0);
        for (Harvest h : harvests) {
            tableModel.addRow(new Object[]{h.getId(), h.getCropName(), h.getFarmId(), h.getQuantity(), h.getDate()});
        }
    }
}