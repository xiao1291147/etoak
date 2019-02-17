package com.demo.guice.server.impl;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.demo.guice.server.OrderService;
import com.demo.guice.server.PriceService;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.util.Modules;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * OrderServiceImpl Tester.
 *
 * @author <Authors name>
 * @since <pre>02/17/2019</pre>
 * @version 1.0
 */
public class OrderServiceImplTest {

    @Inject
    private OrderService orderService;
    // @Inject
    // @Named("supportedCurrencies")
    // private Provider<List<String>> supportedCurrenciesProvider;
    @Inject
    private PriceService priceService;

    @Before
    public void before() throws Exception {
        Guice.createInjector(Modules.override(new ServerModule()).with(new AbstractModule() {
            @Override
            protected void configure() {
                bind(PriceService.class).to(PriceServiceMock.class);
            }
        })).injectMembers(this);
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: sendToPayment(long orderId)
     *
     */
    @Test
    public void testSendToPayment() throws Exception {
        try {
            orderService.sendToPayment(789L);
            fail("Exception expected");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Price=567"));
            assertTrue(e.getMessage().contains("OrdersPaid=1"));
        }
    }

    @Test
    public void testSupportedCurrencies() {
        assertEquals("[CNY, EUR, USD]", priceService.getSupportedCurrencies().toString());
    }
}

class PriceServiceMock extends PriceServiceImpl {

    @Inject
    public PriceServiceMock(Set<String> supportedCurrencies) {
        super(supportedCurrencies, null);
    }

    @Override
    public long getPrice(long orderId) {
        return 567L;
    }
}
