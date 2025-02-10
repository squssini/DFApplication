package com.example.dfapplication;

public class Furniture {

    private String name;
    private  String material;
    private  String price;
    private  String category;
    private String photo;

    public Furniture() {
    }

    public Furniture(String name, String material, String price, String category, String photo) {
        this.name = name;
        this.material = material;
        this.price = price;
        this.category = category;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "name='" + name + '\'' +
                ", material='" + material + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
