package com.grocery.booking.exceptions;

public class GroceryItemOutOfStockException extends RuntimeException{

    public GroceryItemOutOfStockException(String message) {
        super(message);
    }

}
