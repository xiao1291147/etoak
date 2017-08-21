package com.etoak.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 写给大忙人看的javase8
 * Created by xiao1 on 2017/8/21.
 */
public class TestJava8 {

    public static void main(String[] args) {
        System.out.println(Comparator.comparingInt(String::length).compare("first", "second"));
        new Thread(new FutureTask<Void>(() -> {
            System.out.println("callable");
            return null;
        })).start();

        List<Runnable> runners = new ArrayList<>();
        String[] names = {"Moss", "Roy", "Jen"};
        for (String name : names) {
            runners.add(() -> System.out.println(name));
        }
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            runners.add(uncheck(() -> {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(name);
            }));
        }

        runners.forEach(runner -> new Thread(runner).start());
    }

    public static Runnable uncheck(RunnableEx runnableEx) {
        return () -> {
            try {
                runnableEx.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    interface RunnableEx {
        void run() throws Exception;
    }

}
