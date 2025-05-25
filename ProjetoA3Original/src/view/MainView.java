package view;

import controller.FarmController;
import controller.HarvestController;
import controller.InventoryController;
import model.Farm;
import model.Harvest;

import javax.swing.*;
import java.text.ParseException;

public class MainView {
    private FarmController farmController = new FarmController();
    private HarvestController harvestController = new HarvestController();
    private InventoryController inventoryController = new InventoryController(harvestController);
    private FarmView farmView = new FarmView();
    private HarvestView harvestView = new HarvestView();
    private InventoryView inventoryView = new InventoryView();

    public String displayMenu() {
        String menu = "\n======== Menu ========\n" +
                      "1. Cadastrar fazenda\n" +
                      "2. Cadastrar colheitas\n" +
                      "3. Gestão de inventário\n" +
                      "4. Excluir fazenda\n" +
                      "5. Visualizar fazendas cadastradas\n" +
                      "6. Visualizar colheitas cadastradas\n" +
                      "7. Sair\n" +
                      "======================\n" +
                      "Escolha uma opção:";
        return JOptionPane.showInputDialog(menu);
    }

    public void executeOption(int option) throws ParseException {
        switch (option) {
            case 1:
                cadastrarFazenda();
                break;
            case 2:
                cadastrarColheita();
                break;
            case 3:
                gestaoInventario();
                break;
            case 4:
                excluirFazenda();
                break;
            case 5:
                visualizarFazendas();
                break;
            case 6:
                visualizarColheitas();
                break;
            case 7:
                JOptionPane.showMessageDialog(null, "Saindo...");
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
        }
    }

    private void cadastrarFazenda() {
        Farm farm = farmView.getFarmDetails();
        farmController.addFarm(farm);
        JOptionPane.showMessageDialog(null, "Fazenda cadastrada com sucesso!");
    }

    private void cadastrarColheita() throws ParseException {
        Harvest harvest = harvestView.getHarvestDetails();
        harvestController.addHarvest(harvest);
        JOptionPane.showMessageDialog(null, "Colheita cadastrada com sucesso!");
    }

    private void gestaoInventario() {
        while (true) {
            String[] options = {"Adicionar produto", "Remover produto", "Verificar inventário", "Verificar valores dos produtos", "Voltar ao menu principal"};
            int option = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Gestão de Inventário", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (option) {
                case 0:
                    adicionarProduto();
                    break;
                case 1:
                    removerProduto();
                    break;
                case 2:
                    verificarInventario();
                    break;
                case 3:
                    verificarValoresProdutos();
                    break;
                case 4:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }

    private void adicionarProduto() {
        var harvests = harvestController.getHarvests();
        if (harvests.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma colheita cadastrada.");
            return;
        }

        inventoryView.displayHarvestOptions(harvestController.getHarvestOptions());
        String plantingType = inventoryView.getProductToAdd();
        String quantityStr = JOptionPane.showInputDialog("Digite a quantidade a ser adicionada:");
        double quantity = Double.parseDouble(quantityStr);
        try {
            inventoryController.addProduct(plantingType, quantity);
            JOptionPane.showMessageDialog(null, "Produto adicionado ao inventário com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto: " + e.getMessage());
        }
    }

    private void removerProduto() {
        String plantingType = inventoryView.getProductToRemove();
        String quantityStr = JOptionPane.showInputDialog("Digite a quantidade a ser removida:");
        double quantity = Double.parseDouble(quantityStr);
        try {
            inventoryController.removeProduct(plantingType, quantity);
            JOptionPane.showMessageDialog(null, "Produto removido do inventário com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover produto: " + e.getMessage());
        }
    }

    private void verificarInventario() {
        inventoryView.displayInventory(inventoryController.getProducts());
    }

    private void verificarValoresProdutos() {
        inventoryView.displayProductValues(inventoryController.getProductValues(), inventoryController.getProductQuantities());
    }

    private void excluirFazenda() {
        String farmName = farmView.getFarmNameToDelete();
        farmController.removeFarm(farmName);
        JOptionPane.showMessageDialog(null, "Fazenda excluída com sucesso!");
    }

    private void visualizarFazendas() {
        farmView.displayFarms(farmController.getFarms());
    }

    private void visualizarColheitas() {
        harvestView.displayHarvests(harvestController.getHarvests());
    }

    public static void main(String[] args) throws ParseException {
        MainView mainView = new MainView();
        while (true) {
            String optionStr = mainView.displayMenu();
            if (optionStr == null) {
                JOptionPane.showMessageDialog(null, "Saindo...");
                System.exit(0);
            }
            int option = Integer.parseInt(optionStr);
            mainView.executeOption(option);
        }
    }
}