package model;

public class Harvest {
    private int id;
    private String cropName;
    private int farmId;
    private int quantity;
    private String date;

    public Harvest(int id, String cropName, int farmId, int quantity, String date) {
        this.id = id;
        this.cropName = cropName;
        this.farmId = farmId;
        this.quantity = quantity;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getCropName() {
        return cropName;
    }

    public int getFarmId() {
        return farmId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }
}