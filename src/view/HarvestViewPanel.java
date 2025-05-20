package view;

import controller.HarvestController;
import model.Harvest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HarvestViewPanel extends JPanel {
    private JTextField cropNameField, farmIdField, quantityField, dateField;
    private JTable table;
    private DefaultTableModel tableModel;
    private HarvestController harvestController;

    public HarvestViewPanel(HarvestController harvestController) {
        this.harvestController = harvestController;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        cropNameField = new JTextField();
        farmIdField = new JTextField();
        quantityField = new JTextField();
        dateField = new JTextField();
        JButton saveButton = new JButton("Salvar Colheita");

        formPanel.add(new JLabel("Tipo de Plantio:"));
        formPanel.add(cropNameField);
        formPanel.add(new JLabel("ID da Fazenda:"));
        formPanel.add(farmIdField);
        formPanel.add(new JLabel("Quantidade:"));
        formPanel.add(quantityField);
        formPanel.add(new JLabel("Data:"));
        formPanel.add(dateField);
        formPanel.add(new JLabel(""));
        formPanel.add(saveButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Plantio", "Fazenda", "Quantidade", "Data"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        saveButton.addActionListener(e -> {
            String cropName = cropNameField.getText();
            int farmId = Integer.parseInt(farmIdField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String date = dateField.getText();

            harvestController.addHarvest(cropName, farmId, quantity, date);
            updateTable();
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