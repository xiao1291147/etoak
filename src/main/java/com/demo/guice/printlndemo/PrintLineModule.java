package com.demo.guice.printlndemo;

import com.demo.guice.Applets;
import com.google.inject.AbstractModule;

/**
 * PrintLineModule
 * @author xiao1
 * @date 2019/2/17
 */
public class PrintLineModule extends AbstractModule {

    @Override
    protected void configure() {
        Applets.register(binder()).named("println").to(PrintLineApplet.class);
    }
}
