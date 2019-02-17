package com.demo.guice;

import java.util.Arrays;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * CommandLineModule
 * @author xiao1
 * @date 2019/2/17
 */
public class CommandLineModule extends AbstractModule {

    private final String[] args;

    public CommandLineModule(String[] args) {
        this.args = args;
    }

    @Override
    protected void configure() {
    }

    @Provides
    @Args
    private List<String> getCommandLineArgs() {
        return Arrays.asList(args).subList(1, args.length);
    }
}
