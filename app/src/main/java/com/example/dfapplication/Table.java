package com.example.dfapplication;

public class Table extends Furniture {

        private String color;
        private int quantity;
        private int seatsamount;
        private String id;
        private String description;
        private String weight ;
        private String height ;


        public Table(String height, String weight, String description, String id, int seatsamount, int quantity, String color) {
            this.height = height;
            this.weight = weight;
            this.description = description;
            this.id = id;
            this.seatsamount = seatsamount;
            this.quantity = quantity;
            this.color = color;
        }

        public Table(String category, double price, String material, String name, String height, String weight, String description, String id, int seatsamount, int quantity, String color) {
            super(category, price, material, name);
            this.height = height;
            this.weight = weight;
            this.description = description;
            this.id = id;
            this.seatsamount = seatsamount;
            this.quantity = quantity;
            this.color = color;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getSeatsamount() {
            return seatsamount;
        }

        public void setSeatsamount(int seatsamount) {
            this.seatsamount = seatsamount;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

    @Override
    public String toString() {
        return "Table{" +
                "color='" + color + '\'' +
                ", quantity=" + quantity +
                ", seatsamount=" + seatsamount +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}


