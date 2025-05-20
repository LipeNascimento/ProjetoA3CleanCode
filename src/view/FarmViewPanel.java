package view;

import controller.FarmController;
import model.Farm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FarmViewPanel extends JPanel {
    private JTextField nameField, locationField;
    private JTable table;
    private DefaultTableModel tableModel;
    private FarmController farmController;

    public FarmViewPanel(FarmController farmController) {
        this.farmController = farmController;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        nameField = new JTextField();
        locationField = new JTextField();
        JButton saveButton = new JButton("Salvar Fazenda");

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Endereço:"));
        formPanel.add(locationField);
        formPanel.add(new JLabel(""));
        formPanel.add(saveButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Endereço"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String location = locationField.getText();
            if (!name.isEmpty() && !location.isEmpty()) {
                farmController.addFarm(name, location, "", 0);
                updateTable();
                nameField.setText("");
                locationField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            }
        });

        updateTable();
    }

    private void updateTable() {
        List<Farm> farms = farmController.getAllFarms();
        tableModel.setRowCount(0);
        for (Farm farm : farms) {
            tableModel.addRow(new Object[]{farm.getId(), farm.getName(), farm.getLocation()});
        }
    }
}