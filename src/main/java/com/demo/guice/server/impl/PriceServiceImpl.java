package com.demo.guice.server.impl;

import java.util.Set;

import com.demo.guice.server.PriceService;
import com.google.common.cache.Cache;
import javax.inject.Inject;

/**
 * PriceServiceImpl
 * @author xiao1
 * @date 2019/2/17
 */
public class PriceServiceImpl implements PriceService {

    private final Set<String> supportedCurrencies;
    private final Cache<String, String> cache;

    @Inject
    public PriceServiceImpl(Set<String> supportedCurrencies, Cache<String, String> cache) {
        this.supportedCurrencies = supportedCurrencies;
        this.cache = cache;
    }

    @Override
    public long getPrice(long orderId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getSupportedCurrencies() {
        return supportedCurrencies;
    }

    String getCachedValue(String key) {
        return cache.getIfPresent(key);
    }
}
