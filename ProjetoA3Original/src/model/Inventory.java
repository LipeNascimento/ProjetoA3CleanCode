package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<String> products;

    public Inventory() {
        this.products = new ArrayList<>();
    }

    public void addProduct(String product) {
        products.add(product);
    }

    public void removeProduct(String product) {
        products.remove(product);
    }

    public List<String> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "products=" + products +
                '}';
    }
}