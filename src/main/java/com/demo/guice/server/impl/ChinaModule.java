package com.demo.guice.server.impl;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

/**
 * ChinaModule
 * @author xiao1
 * @date 2019/2/17
 */
public class ChinaModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder.newSetBinder(binder(), String.class).addBinding().toInstance("CNY");
    }
}
