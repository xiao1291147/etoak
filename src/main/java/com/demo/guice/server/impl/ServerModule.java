package com.demo.guice.server.impl;

import com.demo.guice.server.OrderService;
import com.demo.guice.server.PaymentService;
import com.demo.guice.server.PriceService;
import com.google.common.cache.Cache;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;

/**
 * ServerModule
 * @author xiao1
 * @date 2019/2/17
 */
public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ChinaModule());
        install(new GlobalModule());

        bind(OrderService.class).to(OrderServiceImpl.class);
        bind(PaymentService.class).to(PaymentServiceImpl.class);
        bind(PriceService.class).to(PriceServiceImpl.class);
        // bind(new TypeLiteral<List<String>>() {
        // }).annotatedWith(Names.named("supportedCurrencies")).toInstance(Arrays.asList("CNY", "USD", "EUR"));

        bind(new TypeLiteral<Cache<String, String>>() {
        }).to(GuiceDemoCache.class);
        // bind(new TypeLiteral<Cache<String, String>>() {
        // }).to(GuiceDemoCache.class).in(Singleton.class);

        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        requestInjection(loggingInterceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Logged.class), loggingInterceptor);
    }

    @Provides
    @SessionId
    private Long generateSessionId() {
        return System.currentTimeMillis();
    }

    // @Provides
    // @Named("supportedCurrencies")
    // private List<String> getSupportedCurrencies(PriceService priceService) {
    //     return priceService.getSupportedCurrencies();
    // }

    // @Provides
    // @Singleton
    // private Cache<String, String> getCache() {
    //     return new GuiceDemoCache();
    // }
}
