package com.demo.pattern.factory;

/**
 * 圣诞系列加工厂
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class MCFactory implements PersonFactory {

    @Override
    public Boy getBoy() {
        return new MCBoy();
    }

    @Override
    public Girl getGirl() {
        return new MCGirl();
    }
}
