
package view;

import controller.FarmController;
import model.Farm;

import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FarmViewPanel extends JPanel {

    private final FarmController controller;
    private final DefaultTableModel tableModel;

    public FarmViewPanel(FarmController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        final JTextField nameField = new JTextField();
        final JTextField addressField = new JTextField();
        final JTextField plantingTypeField = new JTextField();
        final JTextField cultivationAreaField = new JTextField();
        final JLabel statusLabel = new JLabel(" ");
        JButton saveButton = new JButton("Salvar Fazenda", UIManager.getIcon("FileView.floppyDriveIcon"));

        formPanel.setBorder(BorderFactory.createTitledBorder("Nova Fazenda"));
        formPanel.add(new JLabel("Nome:")); formPanel.add(nameField);
        formPanel.add(new JLabel("Endereço:")); formPanel.add(addressField);
        formPanel.add(new JLabel("Tipo de Plantio:")); formPanel.add(plantingTypeField);
        formPanel.add(new JLabel("Área de Cultivo (m²):")); formPanel.add(cultivationAreaField);
        formPanel.add(saveButton); formPanel.add(statusLabel);

        // Tabela de listagem
        String[] columnNames = {"Nome", "Endereço", "Tipo de Plantio", "Área (m²)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable farmTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(farmTable);

        // Layout principal
        add(formPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String plantingType = plantingTypeField.getText();
                String areaText = cultivationAreaField.getText();

                try {
                    double area = Double.parseDouble(areaText);
                    if (name.isEmpty() || address.isEmpty() || plantingType.isEmpty()) {
                        statusLabel.setText("Preencha todos os campos.");
                    } else {
                        Farm farm = new Farm(name, address, plantingType, area);
                        controller.addFarm(farm);
                        tableModel.addRow(new Object[]{name, address, plantingType, area});
                        statusLabel.setText("Fazenda \"" + name + "\" cadastrada!");

                        nameField.setText("");
                        addressField.setText("");
                        plantingTypeField.setText("");
                        cultivationAreaField.setText("");
                    }
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Área inválida.");
                }
            }
        });
    }
}
