package com.demo.pattern.strategy;

/**
 * 用火箭飞行
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class FlyWithRocket implements FlyingStragety {

    @Override
    public void performFly() {
        System.out.println("用火箭在太空遨游");
    }
}
