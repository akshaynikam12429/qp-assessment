package com.grocery.booking.dto;

import com.grocery.booking.converter.JpaPayloadConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;

@Entity(name = "OrderDetails")
public class Order {

    @Id
    @Column(name = "OrderId")
    private Long orderId;

    @Convert(converter = JpaPayloadConverter.class)
    @Column(name = "Items", insertable = true, updatable = true, nullable = true,
        columnDefinition = "json")
    private List<Grocery> groceries;

    @Column(name = "TotalAmount")
    private double totalAmount;

    public Order() {
    }

    public Order(Long orderId, List<Grocery> groceries, double totalAmount) {
        this.orderId = orderId;
        this.groceries = groceries;
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Grocery> getGroceries() {
        return groceries;
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceries = groceries;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
