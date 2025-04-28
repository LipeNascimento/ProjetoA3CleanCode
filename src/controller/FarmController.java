package controller;

import model.Farm;

import java.util.ArrayList;
import java.util.List;

public class FarmController {
    private List<Farm> farms = new ArrayList<>();

    public void addFarm(Farm farm) {
        farms.add(farm);
    }

    public void removeFarm(String name) {
        farms.removeIf(farm -> farm.getName().equalsIgnoreCase(name));
    }

    public List<Farm> getFarms() {
        return farms;
    }
}