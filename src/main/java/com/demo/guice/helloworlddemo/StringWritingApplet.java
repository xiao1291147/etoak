package com.demo.guice.helloworlddemo;

import com.demo.guice.MyApplet;
import com.google.inject.Provider;
import javax.inject.Inject;

/**
 * StringWritingApplet
 * @author xiao1
 * @date 2019/2/17
 */
public class StringWritingApplet implements MyApplet {

    private MyDestination destination;
    private Provider<String> stringProvider;

    @Inject
    public StringWritingApplet(MyDestination destination, @Output Provider<String> stringProvider) {
        this.destination = destination;
        this.stringProvider = stringProvider;
    }

    private void writeString() {
        destination.write(stringProvider.get());
    }

    public void run() {
        writeString();
    }
}
