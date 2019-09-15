package com.demo.pattern.proxy.cglibproxy;

/**
 * cglib测试类
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class Client {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        Train t = (Train) proxy.getProxy(Train.class);
        t.move();
    }
}
