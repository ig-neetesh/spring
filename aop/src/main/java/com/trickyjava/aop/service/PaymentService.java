package com.trickyjava.aop.service;

import com.trickyjava.aop.annotation.PaymentProcessFinish;
import com.trickyjava.aop.annotation.PaymentProcessInit;
import com.trickyjava.aop.model.Order;
import com.trickyjava.aop.model.PaymentMode;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private Double maxAmountForTransaction = 10000d;

    @PaymentProcessInit
    public String initiate() {
        String transactionId = "payment-transaction-id";
        System.out.printf("\n1/4. Payment initiated with transactionId(%s)", transactionId);
        return transactionId;
    }

    public void selectPaymentModeForOrder(PaymentMode paymentMode, Order order) {
        order.setPaymentMode(paymentMode);
        System.out.printf("\n2/4. Chosen payment mode");
    }

    public void provideDetails() {
        System.out.printf("\n3/4. Details provided");
    }

    @PaymentProcessFinish
    public void payFor(Order order) throws RuntimeException {
        if (order.getTotalPrice() > maxAmountForTransaction) {
            throw new RuntimeException(String.format("Transaction above $%s is not allowed.", maxAmountForTransaction));
        }
        finish(order);
    }

    private void finish(Order order) {
        System.out.printf("\n4/4. Transaction(%s) finished. Order placed successfully.\n", order.getPaymentTransactionId());
    }

}
