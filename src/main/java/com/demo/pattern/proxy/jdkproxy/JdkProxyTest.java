package com.demo.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.demo.pattern.proxy.Car;
import com.demo.pattern.proxy.Moveable;

/**
 * JDK动态代理测试类
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        Car car = new Car();
        InvocationHandler h = new TimeHandler(car);
        Class<?> cls = car.getClass();
        /*
            loader 类加载器
            interfaces 实现的接口
            h InvocationHandler

            动态代理实现思路
            实现功能：通过Proxy的newProxyInstance返回代理对象
            1、声明一段源码（动态产生代理）
            2、编译源码（JDK Compiler API），产生新的类（代理类）
            3、将这个类load到内存当中，产生一个新的对象（代理对象）
            4、return代理对象
         */
        Moveable m = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), h);
        System.out.println("代理类名字：" + m.getClass().getName());
        m.move();
    }
}
