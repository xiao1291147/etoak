package com.demo.pattern.cor;

import java.util.concurrent.ThreadLocalRandom;

import com.demo.pattern.cor.handler.PriceHandler;
import com.demo.pattern.cor.handler.PriceHandlerFactory;

/**
 * 客户，请求折扣
 *
 * @author xiaol
 * @date 2019/9/13
 */
public class Customer {

    private PriceHandler priceHandler;

    public void setPriceHandler(PriceHandler priceHandler) {
        this.priceHandler = priceHandler;
    }

    public void requestDiscount(float discount) {
        priceHandler.processDiscount(discount);
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setPriceHandler(PriceHandlerFactory.createPriceHandler());

        for (int i = 0; i < 100; i++) {
            System.out.print(i + ":");
            customer.requestDiscount(ThreadLocalRandom.current().nextFloat());
        }
    }
}
