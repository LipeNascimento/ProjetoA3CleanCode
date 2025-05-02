
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

        tabbedPane.addTab("🏡 Fazendas", new ImageIcon(((ImageIcon) UIManager.getIcon("FileView.directoryIcon")).getImage()), new FarmViewPanel(farmController), "Gerenciar Fazendas");
        Icon folderIcon = UIManager.getIcon("FileChooser.newFolderIcon");
        tabbedPane.addTab("🌾 Colheitas", new ImageIcon(((ImageIcon) folderIcon).getImage()), new HarvestViewPanel(harvestController), "Registrar Colheitas");
        Icon fileIcon = UIManager.getIcon("FileView.fileIcon");
        tabbedPane.addTab("📦 Estoque", new ImageIcon(((ImageIcon) fileIcon).getImage()), new InventoryViewPanel(inventoryController), "Gerenciar Estoque");

        add(tabbedPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
