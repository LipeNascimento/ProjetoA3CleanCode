package view;

import controller.FarmController;
import model.Farm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FarmViewPanel extends JPanel {
    private final JTextField nameField, locationField;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final FarmController farmController;

    public FarmViewPanel(FarmController farmController) {
        this.farmController = farmController;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        nameField = new JTextField();
        locationField = new JTextField();
        JButton saveButton = new JButton("Salvar Fazenda");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Excluir");

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Endereço:"));
        formPanel.add(locationField);
        formPanel.add(saveButton);
        formPanel.add(editButton);
        formPanel.add(deleteButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Endereço"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

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

        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                String name = nameField.getText();
                String location = locationField.getText();
                farmController.updateFarm(id, name, location);
                updateTable();
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                farmController.deleteFarm(id);
                updateTable();
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