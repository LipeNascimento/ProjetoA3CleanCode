package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Harvest {
    private double capacity;
    private String plantingType;
    private String weatherConditions;
    private double quantityProduced;
    private Date harvestDate;
    private double unitPrice;
    private double remainingQuantity;

    public Harvest(double capacity, String plantingType, String weatherConditions, double quantityProduced, Date harvestDate, double unitPrice) {
        this.capacity = capacity;
        this.plantingType = plantingType;
        this.weatherConditions = weatherConditions;
        this.quantityProduced = quantityProduced;
        this.harvestDate = harvestDate;
        this.unitPrice = unitPrice;
        this.remainingQuantity = quantityProduced;
    }

    public String getPlantingType() {
        return plantingType;
    }

    public double getRemainingQuantity() {
        return remainingQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void reduceQuantity(double quantity) {
        if (quantity <= remainingQuantity) {
            remainingQuantity -= quantity;
        } else {
            throw new IllegalArgumentException("Quantidade insuficiente na colheita.");
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("Capacidade: %.2f\nTipo de Plantio: %s\nCondições Climáticas: %s\nQuantidade Produzida: %.2f\nData da Colheita: %s\nPreço por Unidade: R$ %.2f", 
                              capacity, plantingType, weatherConditions, quantityProduced, dateFormat.format(harvestDate), unitPrice);
    }

    // Construtor adicional para simplificação no painel de cadastro
    public Harvest(double capacity, String plantingType, Date harvestDate) {
        this(capacity, plantingType, "Não informado", capacity, harvestDate, 0.0);
    }

}