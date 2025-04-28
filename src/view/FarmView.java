package view;

import model.Farm;

import javax.swing.*;
import java.util.List;

public class FarmView {

    public Farm getFarmDetails() {
        String name = JOptionPane.showInputDialog("Digite o nome da fazenda:");
        String address = JOptionPane.showInputDialog("Digite o endereço da fazenda:");
        String plantingType = JOptionPane.showInputDialog("Digite o tipo de plantio:");
        String cultivationAreaStr = JOptionPane.showInputDialog("Digite a área de cultivo em metros quadrados:");
        double cultivationArea = Double.parseDouble(cultivationAreaStr);

        return new Farm(name, address, plantingType, cultivationArea);
    }

    public String getFarmNameToDelete() {
        return JOptionPane.showInputDialog("Digite o nome da fazenda a ser excluída:");
    }

    public void displayFarms(List<Farm> farms) {
        if (farms.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma fazenda cadastrada.");
        } else {
            StringBuilder farmList = new StringBuilder("Fazendas cadastradas:\n");
            for (Farm farm : farms) {
                farmList.append(farm.toString()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, farmList.toString());
        }
    }
}