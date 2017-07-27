package com.trickyjava.aop.aspect;

import com.trickyjava.aop.model.Order;
import com.trickyjava.aop.model.PaymentMode;
import com.trickyjava.aop.service.EmailService;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PaymentAspect {

    @Autowired
    EmailService emailService;

    /**
     * Sends email when any payment initiated
     * @param id
     */
    @AfterReturning(value = "anyPaymentInit()", returning = "id")
    public void paymentInit(String id) {
        String to = "user@example.com";
        String subject = "Payment Initiated";
        String message = String.format("Payment for order id(%s) Initiated", id);
        emailService.sendEmail(to, subject, message);
    }

    /**
     * Displays the payment mode selection
     * @param order
     * @param paymentMode
     */
    @Before(value = "args(paymentMode, order)")
    public void paymentModeSelected(Order order, PaymentMode paymentMode) {
        System.out.printf("\n>> Payment mode(%s) is selected for order with id %s", paymentMode.name(), order.getPaymentTransactionId());
    }

    /**
     * Sends email after successful payment
     * @param order
     */
    @AfterReturning(value = "anyPaymentFinish() && acceptsOrderAsArgs(order)", argNames = "order")
    public void successfulPayment(Order order) {
        String to = "user@example.com";
        String subject = String.format("Order #%s successfully placed", order.getPaymentTransactionId());
        String message = String.format("Order with Items count: %d", order.getItems().size());
        emailService.sendEmail(to, subject, message);
    }

    /**
     * Sends email after failed payment
     * @param order
     * @param ex
     */
    @AfterThrowing(value = "anyPaymentFinish() && acceptsOrderAsArgs(order)", throwing = "ex")
    public void failedPayment(Order order, Exception ex) {
        String to = "user@example.com";
        String subject = String.format("Payment for order #%s failed", order.getPaymentTransactionId());
        String message = String.format("Payment failed with exception : %s", ex.getMessage());
        emailService.sendEmail(to, subject, message);
    }

    // ------------Pointcut--------------

    @Pointcut("args(order)")
    private void acceptsOrderAsArgs(Order order) {
    }

    @Pointcut("args(paymentMode)")
    private void acceptsPaymentModeAsArgs(PaymentMode paymentMode) {
    }

    @Pointcut("@annotation(com.trickyjava.aop.annotation.PaymentProcessInit)")
    private void anyPaymentInit() {
    }

    @Pointcut("@annotation(com.trickyjava.aop.annotation.PaymentProcessFinish)")
    private void anyPaymentFinish() {
    }
}
