package com.trickyjava.aop.model;

import java.util.List;

public class Order {
    private String paymentTransactionId;
    private List<Item> items;
    private Double totalPrice;
    private PaymentMode paymentMode;

    public Order(String paymentTransactionId, List<Item> items, Double totalPrice) {
        this.paymentTransactionId = paymentTransactionId;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public List<Item> getItems() {
        return items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
}
