package com.demo.pattern.proxy;

/**
 * 动态代理类实现测试类
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception {
        Car car = new Car();
        TimeHandler h = new TimeHandler(car);
        Moveable m = (Moveable) Proxy.newProxyInstance(Moveable.class, h);
        m.move();
    }
}
