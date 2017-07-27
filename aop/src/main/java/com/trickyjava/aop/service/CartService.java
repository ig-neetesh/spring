package com.trickyjava.aop.service;

import com.trickyjava.aop.model.Cart;
import com.trickyjava.aop.model.Item;
import com.trickyjava.aop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    PaymentService paymentService;

    public void addItemToCart(Item item, Cart cart) {
        cart.addItem(item);
        System.out.printf("\nItem(%s) worth($%f) has been added to your cart. Total price : $%f", item.getName(), item.getPrice(), cart.getTotalPrice());
    }

    public void removeItemFromCart(Item item, Cart cart) {
        cart.addItem(item);
        System.out.printf("\nItem(%s) worth($%f) has been added to your cart. Total price : $%f", item.getName(), item.getPrice(), cart.getTotalPrice());
    }

    public Order checkout(Cart cart) {
        String transactionId = paymentService.initiate();
        return new Order(transactionId, cart.getItems(), cart.getTotalPrice());
    }
}
