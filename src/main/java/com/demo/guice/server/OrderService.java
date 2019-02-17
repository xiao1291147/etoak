package com.demo.guice.server;

/**
 * OrderService
 * @author xiao1
 * @date 2019/2/17
 */
public interface OrderService {

    void sendToPayment(long orderId);
}
