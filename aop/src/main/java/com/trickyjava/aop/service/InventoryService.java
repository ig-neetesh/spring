package com.trickyjava.aop.service;

import com.trickyjava.aop.model.Item;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    public void addItem(Item item) {
        System.out.printf("\n[InventoryService] Item(%s) price(%f) added to inventory\n", item.getName(), item.getPrice());
    }

    public void removeItem(Item item) {
        System.out.printf("\n[InventoryService] Item(%s) price(%f) removed from inventory\n", item.getName(), item.getPrice());
    }


}
