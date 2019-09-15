package com.demo.pattern.factory;

/**
 * 右偏分发型
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class RightHair implements HairInterface {

    /**
     * 画了一个右偏分发型
     */
    @Override
    public void draw() {
        System.out.println("--------右偏分发型-----------");
    }
}
