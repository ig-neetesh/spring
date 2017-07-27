package com.trickyjava.aop.service;

import com.trickyjava.aop.model.Cart;
import com.trickyjava.aop.model.Item;
import com.trickyjava.aop.model.Order;
import com.trickyjava.aop.model.PaymentMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class CustomerSpec {

    @Autowired
    CartService cartService;

    @Autowired
    PaymentService paymentService;

    @Test
    public void testCart() {
        List<Map<String, String>> maps = Arrays.<Map<String, String>>asList(
                new HashMap<String, String>() {{
                    put("target", "successful");
                    put("price", "120");
                }},
                new HashMap<String, String>() {{
                    put("target", "fail");
                    put("price", "12000");
                }}
        );
        for (Map<String, String> map : maps) {
            Cart cart = new Cart();
            Item a = new Item("Item A", 200d);
            cartService.addItemToCart(a, cart);
            cartService.addItemToCart(new Item("Item B", 300d), cart);
            cartService.addItemToCart(new Item("Item C", 400d), cart);
            cartService.addItemToCart(new Item("Item D", Double.valueOf(map.get("price"))), cart);

            cartService.removeItemFromCart(a, cart);

            Order order = cartService.checkout(cart);
            paymentService.selectPaymentModeForOrder(PaymentMode.DEBIT_CARD, order);
            paymentService.provideDetails();
            paymentService.payFor(order);
        }
    }
}
