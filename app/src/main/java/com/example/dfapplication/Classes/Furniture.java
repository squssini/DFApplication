package com.example.dfapplication.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Furniture  implements Parcelable {

    private String color;
    private  String material;
    private  String price;
    private  String category;
    private String photo;
    private int cartQty = 1;


    public Furniture() {
    }

    public Furniture(String color, String material, String price, String category, String photo) {
        this.color = color;
        this.material = material;
        this.price = price;
        this.category = category;
        this.photo = photo;
    }
    public int getCartQty() {
        return cartQty;
    }

    public void setCartQty(int cartQty) {
        this.cartQty = cartQty;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public static final Creator<Furniture> CREATOR = new Creator<Furniture>() {
        @Override
        public Furniture createFromParcel(Parcel in) {
            return new Furniture(in);
        }

        @Override
        public Furniture[] newArray(int size) {
            return new Furniture[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    protected Furniture(Parcel in) {
        color = in.readString();
        material = in.readString();
        price = in.readString();
        category = in.readString();
        photo = in.readString();
        cartQty = in.readInt();  // <-- add this
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(color);
        dest.writeString(material);
        dest.writeString(price);
        dest.writeString(category);
        dest.writeString(photo);
        dest.writeInt(cartQty); // <-- add this
    }


    @Override
    public String toString() {
        return "Furniture{" +
                "color='" + color + '\'' +
                ", phoneNum='" + '\'' +
                ", material='" + material + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
