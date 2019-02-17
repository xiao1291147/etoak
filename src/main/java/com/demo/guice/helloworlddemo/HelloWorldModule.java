package com.demo.guice.helloworlddemo;

import java.io.PrintStream;
import java.util.List;

import com.demo.guice.Applets;
import com.demo.guice.Args;
import com.demo.guice.MyApplet;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

/**
 * HelloWorldModule
 * @author xiao1
 * @date 2019/2/17
 */
public class HelloWorldModule extends AbstractModule {

    @Override
    protected void configure() {
        Applets.register(binder()).named("hello").to(StringWritingApplet.class);
        bind(MyApplet.class).annotatedWith(Names.named("hello")).to(StringWritingApplet.class);
        bind(MyDestination.class).to(PrintStreamWriter.class);
        bind(PrintStream.class).toInstance(System.out);
        // bind(String.class).annotatedWith(Output.class).toInstance("Hello World!");
    }

    @Provides
    @Output
    private String getOutputString(@Args List<String> args) {
        return args.get(0);
    }
}
