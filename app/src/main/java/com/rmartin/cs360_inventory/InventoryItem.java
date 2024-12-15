package com.rmartin.cs360_inventory;

public class InventoryItem {

    private String name;
    private int count;
    private String description;
    private String imageUrl;

    public InventoryItem(String name, int count, String description) {
        this.name = name;
        this.count = count;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }


}
