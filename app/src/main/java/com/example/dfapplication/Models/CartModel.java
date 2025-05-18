package com.example.dfapplication.Models;

public class CartModel {
    private String name;
    private String image;
    private String price;
    private int quantity;

    public CartModel() {}

    public CartModel(String name, String image, String price, int quantity) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public String getImage() { return image; }
    public String getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setName(String name) { this.name = name; }
    public void setImage(String image) { this.image = image; }
    public void setPrice(String price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
