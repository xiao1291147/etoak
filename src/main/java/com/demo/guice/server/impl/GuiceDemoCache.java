package com.demo.guice.server.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.cache.AbstractCache;
import javax.annotation.Nullable;
import javax.inject.Singleton;

/**
 * GuiceDemoCache
 * @author xiao1
 * @date 2019/2/17
 */
@Singleton
public class GuiceDemoCache extends AbstractCache<String, String> {

    private final Map<String, String> keyValues = new ConcurrentHashMap<>();

    @Nullable
    @Override
    public String getIfPresent(Object key) {
        return keyValues.get(key);
    }

    @Override
    public void put(String key, String value) {
        keyValues.put(key, value);
    }
}
