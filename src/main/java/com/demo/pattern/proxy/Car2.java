package com.demo.pattern.proxy;

/**
 * 车2
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class Car2 extends Car {

    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车开始行驶...");

        super.move();

        System.out.println("汽车结束行驶... 汽车行驶时间：" + (System.currentTimeMillis() - startTime) + "毫秒！");
    }
}
