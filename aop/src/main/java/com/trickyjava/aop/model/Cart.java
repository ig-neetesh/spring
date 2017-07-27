package com.trickyjava.aop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> items = new ArrayList<>();
    private double totalPrice;

    public List<Item> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addItem(Item item) {
        totalPrice += item.getPrice();
        items.add(item);
    }

    public void removeItem(Item item) {
        totalPrice -= item.getPrice();
        items.remove(item);
    }
}
