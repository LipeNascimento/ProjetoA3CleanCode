
package view;

import controller.InventoryController;

import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class InventoryViewPanel extends JPanel {

    private final InventoryController controller;
    private final DefaultTableModel tableModel;

    public InventoryViewPanel(InventoryController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        final JTextField productField = new JTextField();
        final JTextField quantityField = new JTextField();
        JButton addButton = new JButton("Adicionar", UIManager.getIcon("OptionPane.informationIcon"));
        JButton removeButton = new JButton("Remover", UIManager.getIcon("OptionPane.errorIcon"));
        final JLabel statusLabel = new JLabel(" ");

        topPanel.setBorder(BorderFactory.createTitledBorder("Movimentar Produto"));
        topPanel.add(new JLabel("Produto:")); topPanel.add(productField);
        topPanel.add(new JLabel("Quantidade:")); topPanel.add(quantityField);
        topPanel.add(addButton); topPanel.add(removeButton);

        String[] columnNames = {"Produto", "Quantidade (kg)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable inventoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        refreshTable();

        ActionListener handler = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String produto = productField.getText();
                String qtdeTxt = quantityField.getText();

                try {
                    double qtde = Double.parseDouble(qtdeTxt);
                    if (qtde <= 0 || produto.isEmpty()) {
                        statusLabel.setText("Preencha os campos corretamente.");
                        return;
                    }

                    if (e.getSource() == addButton) {
                        controller.addProduct(produto, qtde);
                    } else {
                        controller.removeProduct(produto, qtde);
                    }

                    refreshTable();
                    statusLabel.setText("Movimentação registrada.");
                    productField.setText("");
                    quantityField.setText("");

                } catch (NumberFormatException ex) {
                    statusLabel.setText("Quantidade inválida.");
                }
            }
        };

        addButton.addActionListener(handler);
        removeButton.addActionListener(handler);
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Limpar tabela
        for (Map.Entry<String, Double> entry : controller.getInventory().entrySet()) {
            tableModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
    }
}
