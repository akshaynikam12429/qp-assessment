package com.grocery.booking.dto;

import java.util.List;

public class OrderReceipt {

    private List<Grocery> groceries;
    private String totalAmount;

    public OrderReceipt(List<Grocery> groceries, String totalAmount) {
        this.groceries = groceries;
        this.totalAmount = totalAmount;
    }

    public OrderReceipt() {

    }

    public List<Grocery> getGroceries() {
        return groceries;
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceries = groceries;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
