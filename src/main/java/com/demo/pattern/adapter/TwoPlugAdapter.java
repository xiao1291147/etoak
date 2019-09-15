package com.demo.pattern.adapter;

/**
 * 二相转三相的插座适配器
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class TwoPlugAdapter implements ThreePlugIf {

    private final GBTwoPlug gbTwoPlug;

    public TwoPlugAdapter(GBTwoPlug gbTwoPlug) {
        this.gbTwoPlug = gbTwoPlug;
    }

    @Override
    public void powerWithThree() {
        System.out.println("通过二相转三相");
        gbTwoPlug.powerWithTwo();
    }
}
