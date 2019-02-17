package com.demo.guice.server.impl;

import com.demo.guice.server.PaymentService;
import com.google.common.cache.Cache;
import javax.inject.Inject;

/**
 * PaymentServiceImpl
 * @author xiao1
 * @date 2019/2/17
 */
public class PaymentServiceImpl implements PaymentService {

    private final Cache<String, String> cache;

    @Inject
    public PaymentServiceImpl(Cache<String, String> cache) {
        this.cache = cache;
    }

    @Override
    @Logged
    public void pay(long orderId, long price, Long sessionId) {
    }

    void putCache(String key, String value) {
        cache.put(key, value);
    }

    String getCacheValue(String key) {
        return cache.getIfPresent(key);
    }
}
