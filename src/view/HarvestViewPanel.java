
package view;

import controller.HarvestController;
import model.Harvest;

import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HarvestViewPanel extends JPanel {

    private final HarvestController controller;
    private final DefaultTableModel tableModel;

    public HarvestViewPanel(HarvestController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        final JTextField capacityField = new JTextField();
        final JTextField plantingTypeField = new JTextField();
        final JTextField harvestDateField = new JTextField();
        JButton saveButton = new JButton("Salvar Colheita", UIManager.getIcon("FileView.floppyDriveIcon"));
        final JLabel statusLabel = new JLabel(" ");

        formPanel.setBorder(BorderFactory.createTitledBorder("Nova Colheita"));
        formPanel.add(new JLabel("Capacidade (kg):")); formPanel.add(capacityField);
        formPanel.add(new JLabel("Tipo de Plantio:")); formPanel.add(plantingTypeField);
        formPanel.add(new JLabel("Data da Colheita (dd/MM/yyyy):")); formPanel.add(harvestDateField);
        formPanel.add(saveButton); formPanel.add(statusLabel);

        String[] columnNames = {"Tipo de Plantio", "Capacidade (kg)", "Data"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable harvestTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(harvestTable);

        add(formPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double capacity = Double.parseDouble(capacityField.getText());
                    String plantingType = plantingTypeField.getText();
                    String dateStr = harvestDateField.getText();

                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);

                    if (plantingType.isEmpty()) {
                        statusLabel.setText("Preencha todos os campos.");
                        return;
                    }

                    Harvest harvest = new Harvest(capacity, plantingType, date);
                    controller.addHarvest(harvest);
                    tableModel.addRow(new Object[]{plantingType, capacity, dateStr});
                    statusLabel.setText("Colheita registrada.");

                    capacityField.setText("");
                    plantingTypeField.setText("");
                    harvestDateField.setText("");

                } catch (Exception ex) {
                    statusLabel.setText("Dados inválidos.");
                }
            }
        });
    }
}
