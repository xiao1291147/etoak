package com.demo.pattern.proxy;

/**
 * 日志代理
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class CarLogProxy implements Moveable {

    private final Moveable m;

    public CarLogProxy(Moveable m) {
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("日志开始...");

        m.move();

        System.out.println("日志结束...");
    }
}
