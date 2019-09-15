package com.demo.pattern.strategy;

/**
 * 超类，所有的鸭子都要继承此类
 * 抽象了鸭子的行为：显示和鸣叫
 *
 * @author xiaol
 * @date 2019/9/14
 */
public abstract class Duck {

    /**
     * 鸭子发出叫声
     * 通用行为，由超类实现
     */
    public void quack() {
        System.out.println("嘎嘎嘎");
    }

    /**
     * 显示鸭子的外观
     * 鸭子的外观各不相同，声明为abstract，由子类实现
     */
    public abstract void display();

    private FlyingStragety flyingStragety;

    public void setFlyingStragety(FlyingStragety flyingStragety) {
        this.flyingStragety = flyingStragety;
    }

    public void fly() {
        flyingStragety.performFly();
    }
}
