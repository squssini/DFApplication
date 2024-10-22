package com.example.dfapplication;

public class Furniture {
    private String name;
    private String material;
    private double price;
    private String category;
    private String photo;

    public Furniture(String name, String category, String price, String material , String photo) {
    }

    public Furniture(String category, double price, String material, String name, String photo) {
        this.category = category;
        this.price = price;
        this.material = material;
        this.name = name;
        this.photo=photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "name='" + name + '\'' +
                ", material='" + material + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
