package com.demo.pattern.proxy;

/**
 * 客户端
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class Client {

    public static void main(String[] args) {
        // 使用继承的方式实现
        Moveable m = new Car2();
        m.move();

        System.out.println("########################");
        // 使用聚合的方式实现
        Moveable m2 = new Car3(new Car());
        m2.move();

        System.out.println("\n########################\n");
        Car car = new Car();
        CarTimeProxy ctp = new CarTimeProxy(car);
        CarLogProxy clp = new CarLogProxy(ctp);
        clp.move();
    }
}
