package model;

public class Inventory {
    private int id;
    private String itemName;
    private int quantity;
    private String unit;

    public Inventory(int id, String itemName, int quantity, String unit) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
}