package com.demo.pattern.strategy;

/**
 * 不会飞行
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class FlyNoWay implements FlyingStragety {

    @Override
    public void performFly() {
        System.out.println("我不会飞行！");
    }
}
