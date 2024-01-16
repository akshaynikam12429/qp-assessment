package com.grocery.booking.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Locale;

@Entity(name = "Grocery")
public class Grocery {

    @Id
    @Column(name = "GroceryId")
    private Long groceryId;
    @Column(name = "Name")
    private String groceryName;
    @Column(name = "Quantity")
    private Double quantity;
    @Column(name = "Price")
    private double price;

    public Grocery(Long groceryId, String groceryName, double quantity, int price) {
        this.groceryId = groceryId;
        this.groceryName = groceryName;
        this.quantity = quantity;
        this.price = price;
    }

    public Grocery() {

    }

    public Long getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(Long groceryId) {
        this.groceryId = groceryId;
    }

    public String getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String groceryName) {
        this.groceryName = groceryName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return String.format(Locale.US, "%,.2f", price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
            "groceryId=" + groceryId +
            ", groceryName='" + groceryName + '\'' +
            ", quantity=" + quantity +
            ", price=" + price +
            '}';
    }
}
