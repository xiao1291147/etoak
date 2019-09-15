package com.demo.pattern.factory;

/**
 * 左偏分发型
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class LeftHair implements HairInterface {

    /**
     * 画了一个左偏分发型
     */
    @Override
    public void draw() {
        System.out.println("--------左偏分发型-----------");
    }
}
