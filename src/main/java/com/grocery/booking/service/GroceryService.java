package com.grocery.booking.service;

import com.grocery.booking.converter.OrderConverter;
import com.grocery.booking.dto.Grocery;
import com.grocery.booking.dto.Order;
import com.grocery.booking.dto.OrderReceipt;
import com.grocery.booking.exceptions.GroceryItemNotFoundException;
import com.grocery.booking.exceptions.GroceryItemOutOfStockException;
import com.grocery.booking.repository.GroceryRepository;
import com.grocery.booking.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroceryService {

    private static final Logger logger = LogManager.getLogger(GroceryService.class);

    @Autowired
    private GroceryRepository groceryRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private OrderRepository orderRepository;

    public List<Grocery> getExistingGroceries() {
        return groceryRepository.findAll();
    }

    public List<Grocery> getAvailableGroceries() {
        logger.debug("Checking available groceries");
        return groceryRepository.findAllByQuantityGreaterThan(0);
    }

    public OrderReceipt bookGroceries(List<Grocery> orderGroceries) throws Exception {
        Order order = new Order();
        double totalAmount = 0;
        for(Grocery grocery : orderGroceries){
            Optional<Grocery> availableGrocery = groceryRepository.findById(grocery.getGroceryId());
            if(availableGrocery.isEmpty()){
                throw new GroceryItemNotFoundException("Grocery item not found with an id: " + grocery.getGroceryId());
            } else {
                if(grocery.getQuantity() > availableGrocery.get().getQuantity()){
                    throw new GroceryItemOutOfStockException("Out of stock item: "+grocery.getGroceryName()+" "
                        + "available quantity is :"+availableGrocery.get().getQuantity());
                } else {
                    totalAmount += grocery.getQuantity() * Double.parseDouble(grocery.getPrice());
                    availableGrocery.get().setQuantity(availableGrocery.get().getQuantity() - grocery.getQuantity());
                    groceryRepository.save(availableGrocery.get());
                }
            }
        }
        order.setGroceries(orderGroceries);
        order.setTotalAmount(totalAmount);
        return orderConverter.createOrderReceipt(order);
    }

    public Grocery addNewGroceryItemInSystem(Grocery newGroceryItem) {
        return groceryRepository.save(newGroceryItem);
    }

    public void removeGroceryItem(Grocery groceryItemToRemove) {
        groceryRepository.deleteById(groceryItemToRemove.getGroceryId());
        logger.debug("Grocery with an id: {} is deleted from system ", groceryItemToRemove.getGroceryId());
    }

    public void updateGroceryDetails(Grocery groceryItemToUpdate) throws Exception {
        if(groceryRepository.existsById(groceryItemToUpdate.getGroceryId())){
            groceryRepository.save(groceryItemToUpdate);
        } else {
            throw new GroceryItemNotFoundException("Grocery is not present");
        }
    }

    public void manageInventoryOfGroceries(Long groceryId, Double quantity) throws Exception {
        if(groceryRepository.existsById(groceryId)){
            Optional<Grocery> grocery = groceryRepository.findById(groceryId);
            Grocery groceryItem = grocery.get();
            groceryItem.setQuantity(groceryItem.getQuantity() + quantity);
            groceryRepository.save(groceryItem);
        } else {
            throw new GroceryItemNotFoundException("Item not found with an id: "+ groceryId);
        }
    }
}
