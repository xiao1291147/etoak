package com.demo.pattern.factory;

/**
 * 新年系列加工厂
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class HNFactory implements PersonFactory {

    @Override
    public Boy getBoy() {
        return new HNBoy();
    }

    @Override
    public Girl getGirl() {
        return new HNGirl();
    }
}
