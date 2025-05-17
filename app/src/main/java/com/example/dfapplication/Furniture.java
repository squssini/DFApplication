package com.example.dfapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Furniture  implements Parcelable {

    private String name;
    private String phoneNum;
    private String color;
    private String numOfFur;
    private String owner;
    private  String material;
    private  String price;
    private  String category;
    private String photo;

    public Furniture() {
    }

    public Furniture(String name, String phoneNum, String color, String numOfFur, String owner, String material, String price, String category, String photo) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.color = color;
        this.numOfFur = numOfFur;
        this.owner = owner;
        this.material = material;
        this.price = price;
        this.category = category;
        this.photo = photo;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNumOfFur() {
        return numOfFur;
    }

    public void setNumOfFur(String numOfFur) {
        this.numOfFur = numOfFur;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Furniture(Parcel in) {
        name = in.readString();
        phoneNum = in.readString();
        color = in.readString();
        numOfFur = in.readString();
        owner = in.readString();
        material = in.readString();
        price = in.readString();
        category = in.readString();
        photo = in.readString();
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

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNum);
        dest.writeString(color);
        dest.writeString(numOfFur);
        dest.writeString(owner);
        dest.writeString(material);
        dest.writeString(price);
        dest.writeString(category);
        dest.writeString(photo);
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", color='" + color + '\'' +
                ", numOfFur='" + numOfFur + '\'' +
                ", owner='" + owner + '\'' +
                ", material='" + material + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
