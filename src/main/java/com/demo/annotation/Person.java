package com.demo.annotation;

/**
 * com.demo.annotation.Person
 *
 * @author xiaol
 * @date 2019/9/22
 */
@Description("I am interface annotation")
public class Person {

    @Description("I am interface method annotation")
    String name() {
        return null;
    }

    int age() {
        return 0;
    }

    void sing() {

    }
}
