package com.demo.guice.server.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * GuiceDemoCache Tester.
 *
 * @author <Authors name>
 * @since <pre>02/17/2019</pre>
 * @version 1.0
 */
public class GuiceDemoCacheTest {

    @Inject
    private PaymentServiceImpl paymentService;
    @Inject
    private PriceServiceImpl priceService;

    @Before
    public void before() throws Exception {
        Guice.createInjector(new ServerModule()).injectMembers(this);
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: getIfPresent(Object key)
     *
     */
    @Test
    public void testGetIfPresent() throws Exception {
    }

    /**
     *
     * Method: put(String key, String value)
     *
     */
    @Test
    public void testPut() throws Exception {
        paymentService.putCache("testKey", "testValue");
        String cachedValue = priceService.getCachedValue("testKey");
        assertEquals("testValue", cachedValue);
    }
}
