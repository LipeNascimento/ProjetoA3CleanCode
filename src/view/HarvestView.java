package view;

import model.Harvest;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HarvestView {

    public Harvest getHarvestDetails() throws ParseException {
        String capacityStr = JOptionPane.showInputDialog("Digite a capacidade de colheita:");
        double capacity = Double.parseDouble(capacityStr);
        String plantingType = JOptionPane.showInputDialog("Digite o tipo de plantio:");
        String weatherConditions = JOptionPane.showInputDialog("Digite as condições climáticas:");
        String quantityProducedStr = JOptionPane.showInputDialog("Digite a quantidade produzida:");
        double quantityProduced = Double.parseDouble(quantityProducedStr);
        String dateStr = JOptionPane.showInputDialog("Digite a data da colheita (dd/MM/yyyy):");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date harvestDate = dateFormat.parse(dateStr);
        String unitPriceStr = JOptionPane.showInputDialog("Digite o valor por unidade em R$:");
        double unitPrice = Double.parseDouble(unitPriceStr);

        return new Harvest(capacity, plantingType, weatherConditions, quantityProduced, harvestDate, unitPrice);
    }

    public void displayHarvests(List<Harvest> harvests) {
        if (harvests.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma colheita cadastrada.");
        } else {
            StringBuilder harvestList = new StringBuilder("Colheitas cadastradas:\n");
            for (Harvest harvest : harvests) {
                harvestList.append(harvest.toString()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, harvestList.toString());
        }
    }
}