package com.demo.pattern.factory;

/**
 * 发型接口测试类
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class HairTest {

    public static void main(String[] args) {
        HairFactory factory = new HairFactory();
        HairInterface left = factory.getHair("left");
        left.draw();

        System.out.println("#############################");
        HairInterface right = factory.getHairByClass("com.demo.pattern.factory.RightHair");
        right.draw();
    }
}
