package com.demo.guice.server.impl;

import com.demo.guice.server.OrderService;
import com.demo.guice.server.PaymentService;
import com.demo.guice.server.PriceService;
import javax.inject.Inject;

/**
 * OrderServiceImpl
 * @author xiao1
 * @date 2019/2/17
 */
public class OrderServiceImpl implements OrderService {

    private final PriceService priceService;
    private final PaymentService paymentService;
    private final SessionManager sessionManager;

    private Long ordersPaid = 0L;

    @Inject
    public OrderServiceImpl(PriceService priceService, PaymentService paymentService, SessionManager sessionManager) {
        this.priceService = priceService;
        this.paymentService = paymentService;
        this.sessionManager = sessionManager;
    }

    @Override
    @Logged
    public void sendToPayment(long orderId) {
        long price = priceService.getPrice(orderId);
        paymentService.pay(orderId, price, sessionManager.getSessionId());
        ordersPaid = ordersPaid + 1;

        throw new RuntimeException("Price=" + price + ". SessionId=" + sessionManager.getSessionId() + ". OrdersPaid=" + ordersPaid);
    }
}