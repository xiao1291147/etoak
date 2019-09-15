package com.demo.pattern.strategy;

/**
 * 橡胶鸭
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class RubberDuck extends Duck {

    public RubberDuck() {
        super();
        super.setFlyingStragety(new FlyNoWay());
    }

    @Override
    public void display() {
        System.out.println("我全身发黄，嘴巴很红");
    }

    @Override
    public void quack() {
        System.out.println("嘎~嘎~嘎~");
    }
}
