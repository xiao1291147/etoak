package com.demo.guice.server;

/**
 * PaymentService
 * @author xiao1
 * @date 2019/2/17
 */
public interface PaymentService {

    void pay(long orderId, long price, Long sessionId);
}
