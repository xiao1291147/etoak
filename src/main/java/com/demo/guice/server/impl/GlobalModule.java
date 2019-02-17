package com.demo.guice.server.impl;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

/**
 * GlobalModule
 * @author xiao1
 * @date 2019/2/17
 */
public class GlobalModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<String> currencyBinder = Multibinder.newSetBinder(binder(), String.class);
        currencyBinder.addBinding().toInstance("EUR");
        currencyBinder.addBinding().toInstance("USD");
    }
}
