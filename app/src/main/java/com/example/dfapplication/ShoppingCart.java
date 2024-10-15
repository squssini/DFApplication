package com.example.dfapplication;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Furniture> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Furniture item) {
        items.add(item);
    }

    public void removeItem(Furniture item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (Furniture item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public List<Furniture> getItems() {
        return items;
    }
}
