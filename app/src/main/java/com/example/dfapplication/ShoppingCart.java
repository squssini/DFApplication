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



    public List<Furniture> getItems() {
        return items;
    }
}
