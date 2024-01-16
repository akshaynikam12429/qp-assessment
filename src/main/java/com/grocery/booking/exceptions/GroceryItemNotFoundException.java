package com.grocery.booking.exceptions;

public class GroceryItemNotFoundException extends RuntimeException{

    public GroceryItemNotFoundException(String message) {
        super(message);
    }

}
