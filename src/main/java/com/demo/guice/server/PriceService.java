package com.demo.guice.server;

import java.util.Set;

/**
 * PriceService
 * @author xiao1
 * @date 2019/2/17
 */
public interface PriceService {

    long getPrice(long orderId);

    Set<String> getSupportedCurrencies();
}
