package com.example.dfapplication;

import java.util.List;

public class Order {
    private int orderId;
    private List<Furniture> items;
    private double totalAmount;

    public Order() {
    }

    public Order(double totalAmount, List<Furniture> items, int orderId) {
        this.totalAmount = totalAmount;
        this.items = items;
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Furniture> getItems() {
        return items;
    }

    public void setItems(List<Furniture> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
