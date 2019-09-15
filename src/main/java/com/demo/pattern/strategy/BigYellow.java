package com.demo.pattern.strategy;

/**
 * 大黄鸭
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class BigYellow extends Duck {

    public BigYellow() {
        super();
        super.setFlyingStragety(new FlyNoWay());
    }

    @Override
    public void display() {
        System.out.println("我身体很大，全身黄黄");
    }

    @Override
    public void quack() {
    }
}
