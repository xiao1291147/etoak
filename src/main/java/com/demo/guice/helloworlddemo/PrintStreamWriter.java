package com.demo.guice.helloworlddemo;

import java.io.PrintStream;

import javax.inject.Inject;

/**
 * PrintStreamWriter
 * @author xiao1
 * @date 2019/2/17
 */
public class PrintStreamWriter implements MyDestination {

    private PrintStream destination;

    @Inject
    public PrintStreamWriter(PrintStream destination) {
        this.destination = destination;
    }

    @Override
    public void write(String string) {
        destination.println(string);
    }
}
