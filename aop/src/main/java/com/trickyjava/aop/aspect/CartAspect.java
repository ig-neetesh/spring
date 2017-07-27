package com.trickyjava.aop.aspect;

import com.trickyjava.aop.model.Cart;
import com.trickyjava.aop.model.Item;
import com.trickyjava.aop.service.InventoryService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CartAspect {

    @Autowired
    InventoryService inventoryService;

    @After("anyItemAddedToCart() && acceptsItemAndCart(c, i)")
    public void updateInventoryWhenItemAddedToCart(Cart c, Item i) {
        inventoryService.removeItem(i);
    }

    @After("anyItemRemovedFromCart() && acceptsItemAndCart(c, i)")
    public void updateInventoryWhenItemRemovedFromCart(Cart c, Item i) {
        inventoryService.addItem(i);
    }

    // --------Pointcut definition -----

    @Pointcut("execution(* com.trickyjava.aop.service.CartService.addItemToCart(..))")
    private void anyItemAddedToCart() {
    }

    @Pointcut("execution(* com.trickyjava.aop.service.CartService.removeItemFromCart(..))")
    private void anyItemRemovedFromCart() {
    }

    @Pointcut("args(it, ca)")
    private void acceptsItemAndCart(Cart ca, Item it) {
    }
}