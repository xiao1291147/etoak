package com.demo.pattern.proxy;

/**
 * 时间代理
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class CarTimeProxy implements Moveable {

    private final Moveable m;

    public CarTimeProxy(Moveable m) {
        this.m = m;
    }

    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车开始行驶...");

        m.move();

        System.out.println("汽车结束行驶... 汽车行驶时间：" + (System.currentTimeMillis() - startTime) + "毫秒！");
    }
}
