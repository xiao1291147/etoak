package com.demo.pattern.strategy;

/**
 * MallardDuck
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        super();
        super.setFlyingStragety(new FlyWithWin());
    }

    @Override
    public void display() {
        System.out.println("我的脖子是绿色的");
    }
}
