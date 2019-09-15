package com.demo.pattern.strategy;

/**
 * 鸭子测试类
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class DuckTest {

    public static void main(String[] args) {
        System.out.println("测试鸭子程序\n");

        Duck duck = new MallardDuck();
        duck.display();
        duck.quack();
        duck.fly();

        System.out.println("#####################");
        duck = new RedheadDuck();
        duck.display();
        duck.quack();
        duck.fly();

        System.out.println("#####################");
        duck = new RubberDuck();
        duck.display();
        duck.quack();
        duck.fly();

        System.out.println("#####################");
        duck = new BigYellow();
        duck.display();
        duck.quack();
        duck.fly();

        System.out.println("#####################");
        duck = new SpaceDuck();
        duck.display();
        duck.quack();
        duck.fly();

        System.out.println("\n测试完毕");
    }
}
