package com.demo.pattern.singleton;

/**
 * 单例模式测试类
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class TestSingleton {

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);

        Singleton2 singleton3 = Singleton2.getInstance();
        Singleton2 singleton4 = Singleton2.getInstance();
        System.out.println(singleton3 == singleton4);
    }
}
