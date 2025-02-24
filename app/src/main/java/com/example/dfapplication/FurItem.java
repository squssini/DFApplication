package com.example.dfapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public  class FurItem implements Parcelable {
    private String nameFur;
    private  String owners;
    private String phone;
    private String color;
    private  String fur_num;
    private  String   price;
    private String photo;

    protected FurItem(Parcel in) {
        nameFur = in.readString();
        owners = in.readString();
        phone = in.readString();
        color = in.readString();
        fur_num = in.readString();
        price = in.readString();
        photo = in.readString();
    }

    public static final Creator<FurItem> CREATOR = new Creator<FurItem>() {
        @Override
        public FurItem createFromParcel(Parcel in) {
            return new FurItem(in);
        }

        @Override
        public FurItem[] newArray(int size) {
            return new FurItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nameFur);
        dest.writeString(owners);
        dest.writeString(phone);
        dest.writeString(color);
        dest.writeString(fur_num);
        dest.writeString(price);
        dest.writeString(photo);
    }

    public FurItem() {
    }

    public FurItem(String photo, String price, String fur_num, String color, String phone, String owners, String nameFur) {
        this.photo = photo;
        this.price = price;
        this.fur_num = fur_num;
        this.color = color;
        this.phone = phone;
        this.owners = owners;
        this.nameFur = nameFur;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFur_num() {
        return fur_num;
    }

    public void setFur_num(String fur_num) {
        this.fur_num = fur_num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public String getNameFur() {
        return nameFur;
    }

    public void setNameFur(String nameFur) {
        this.nameFur = nameFur;
    }

    @Override
    public String toString() {
        return "FurItem{" +
                "nameFur='" + nameFur + '\'' +
                ", owners='" + owners + '\'' +
                ", phone='" + phone + '\'' +
                ", color='" + color + '\'' +
                ", fur_num='" + fur_num + '\'' +
                ", price='" + price + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
