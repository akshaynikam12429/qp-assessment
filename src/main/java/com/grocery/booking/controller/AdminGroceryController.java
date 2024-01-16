package com.grocery.booking.controller;

import com.grocery.booking.dto.Grocery;
import com.grocery.booking.service.GroceryService;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminGroceryController {

    private static final Logger logger = LogManager.getLogger(AdminGroceryController.class);

    @Autowired
    private GroceryService groceryService;

    @PostMapping("/addNewGroceryItem")
    public ResponseEntity<Grocery> addNewGroceryItem(@RequestBody Grocery newGroceryItem){
        logger.debug("Adding new grocery item in the system: {}", newGroceryItem);
        return ResponseEntity.ok(groceryService.addNewGroceryItemInSystem(newGroceryItem));
    }

    @GetMapping("/viewExistingGroceryItems")
    public ResponseEntity<List<Grocery>> viewExistingGroceryItems(){
        logger.debug("Viewing existing groceries");
        return ResponseEntity.ok(groceryService.getExistingGroceries());
    }

    @PostMapping("/removeGroceryItems")
    public ResponseEntity<String> removeGroceryItems(@RequestBody Grocery groceryItemToRemove){
        logger.debug("Removing an item: {} from the system ", groceryItemToRemove);
        groceryService.removeGroceryItem(groceryItemToRemove);
        return ResponseEntity.ok("Item removed from the system successfully");
    }

    @PostMapping("/updateGroceryItemDetails")
    public ResponseEntity<String> updateGroceryDetails(@RequestBody Grocery groceryItemToUpdate)
        throws Exception {
        logger.debug("Updating the grocery item to: {}", groceryItemToUpdate);
        groceryService.updateGroceryDetails(groceryItemToUpdate);
        return ResponseEntity.ok("Item details updated successsfully");
    }

    @PostMapping("/manageGroceryItemDetails")
    public ResponseEntity<String> manageInventoryOfGroceries(@RequestParam Long groceryId,
        @RequestParam Double quantity)
        throws Exception {
        logger.debug("Managing inventory of the grocery with an id: {} with quantity to {}",
            groceryId, quantity);
        groceryService.manageInventoryOfGroceries(groceryId, quantity);
        return ResponseEntity.ok("Inventory managed successsfully");
    }



}
