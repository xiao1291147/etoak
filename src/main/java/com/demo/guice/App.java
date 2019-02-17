package com.demo.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * App
 * @author xiao1
 * @date 2019/2/17
 */
public class App {

    /**
     * bootstrap:
     * parse command line
     * set up environment
     * kick off main logic
     * @param args
     */
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MainModule(), new CommandLineModule(args));
        Applets.get(injector, args[0]).run();
    }

}
