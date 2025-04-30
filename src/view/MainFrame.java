
package view;

import controller.FarmController;
import controller.HarvestController;
import controller.InventoryController;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Sistema de Gestão Agrícola - SmartFarm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        FarmController farmController = new FarmController();
        HarvestController harvestController = new HarvestController();
        InventoryController inventoryController = new InventoryController(harvestController);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Fazendas", new FarmViewPanel(farmController));
        tabbedPane.addTab("Colheitas", new HarvestViewPanel(harvestController));
        tabbedPane.addTab("Estoque", new InventoryViewPanel(inventoryController));

        add(tabbedPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
