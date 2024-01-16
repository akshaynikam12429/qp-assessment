package com.grocery.booking.converter;

import com.grocery.booking.dto.Order;
import com.grocery.booking.dto.OrderReceipt;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    public OrderReceipt createOrderReceipt(Order order){
        OrderReceipt orderReceipt = new OrderReceipt();
        orderReceipt.setGroceries(order.getGroceries());
        orderReceipt.setTotalAmount(String.format(Locale.US, "%,.2f", order.getTotalAmount()));
        return orderReceipt;
    }

}
