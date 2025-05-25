package controller;

import model.Harvest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HarvestController {
    private List<Harvest> harvests = new ArrayList<>();
    private Map<String, Harvest> harvestMap = new HashMap<>();

    public void addHarvest(Harvest harvest) {
        harvests.add(harvest);
        harvestMap.put(harvest.getPlantingType(), harvest);
    }

    public List<Harvest> getHarvests() {
        return harvests;
    }

    public Map<String, Double> getHarvestOptions() {
        Map<String, Double> options = new HashMap<>();
        for (Harvest harvest : harvests) {
            options.put(harvest.getPlantingType(), harvest.getRemainingQuantity());
        }
        return options;
    }

    public Harvest getHarvestByType(String plantingType) {
        return harvestMap.get(plantingType);
    }

    public void reduceQuantity(String plantingType, double quantity) {
        Harvest harvest = harvestMap.get(plantingType);
        if (harvest != null) {
            harvest.reduceQuantity(quantity);
        }
    }
}