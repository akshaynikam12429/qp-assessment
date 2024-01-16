package com.grocery.booking.controller;

import com.grocery.booking.dto.Grocery;
import com.grocery.booking.dto.OrderReceipt;
import com.grocery.booking.service.GroceryService;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserGroceryController {

    private static final Logger logger = LogManager.getLogger(UserGroceryController.class);
    @Autowired
    private GroceryService groceryService;

    @RequestMapping(value ="/availableGroceries")
    public ResponseEntity<List<Grocery>> getGroceries(){
        logger.debug("Checking available groceries");
        return ResponseEntity.ok(groceryService.getAvailableGroceries());
    }

    @PostMapping("/bookGroceries")
    public ResponseEntity<OrderReceipt> bookGroceryItems(@RequestBody List<Grocery> orderGroceries)
        throws Exception {
        logger.debug("Order is being placed for groceries {}", orderGroceries);
        OrderReceipt orderReceipt = groceryService.bookGroceries(orderGroceries);
        logger.debug("Receipt of an order is: {}", orderReceipt);
        return ResponseEntity.ok(orderReceipt);
    }

}
