package com.demo.pattern.proxy;

/**
 * 车3
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class Car3 implements Moveable {

    private final Car car;

    public Car3(Car car) {
        this.car = car;
    }

    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车开始行驶...");

        car.move();

        System.out.println("汽车结束行驶... 汽车行驶时间：" + (System.currentTimeMillis() - startTime) + "毫秒！");
    }
}
