package com.demo.guice;

import com.demo.guice.helloworlddemo.HelloWorldModule;
import com.demo.guice.printlndemo.PrintLineModule;
import com.google.inject.AbstractModule;

/**
 * MainModule
 * @author xiao1
 * @date 2019/2/17
 */
public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new HelloWorldModule());
        install(new PrintLineModule());
    }
}
