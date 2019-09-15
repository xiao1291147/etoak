package com.demo.pattern.strategy;

/**
 * 振翅高飞
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class FlyWithWin implements FlyingStragety {

    @Override
    public void performFly() {
        System.out.println("振翅高飞");
    }
}
