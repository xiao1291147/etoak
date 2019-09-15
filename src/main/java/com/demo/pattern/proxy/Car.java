package com.demo.pattern.proxy;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 车
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class Car implements Moveable {

    /**
     * 实现开车
     */
    @Override
    public void move() {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000));
            System.out.println("汽车行驶中...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
