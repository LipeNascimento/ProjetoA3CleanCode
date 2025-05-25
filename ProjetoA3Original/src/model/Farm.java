package model;

public class Farm {
    private String name;
    private String address;
    private String plantingType;
    private double cultivationArea;

    public Farm(String name, String address, String plantingType, double cultivationArea) {
        this.name = name;
        this.address = address;
        this.plantingType = plantingType;
        this.cultivationArea = cultivationArea;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPlantingType() {
        return plantingType;
    }

    public double getCultivationArea() {
        return cultivationArea;
    }

    @Override
    public String toString() {
        return String.format("Fazenda: %s\nEndereço: %s\nTipo de Plantio: %s\nÁrea de Cultivo: %.2f metros quadrados", 
                              name, address, plantingType, cultivationArea);
    }
}