package com.demo.pattern.adapter;

/**
 * 采用继承方式的插座适配器
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class TwoPlugAdapterExtends extends GBTwoPlug implements ThreePlugIf {

    @Override
    public void powerWithThree() {
        System.out.print("借助继承适配器使用二相电流输出");
        this.powerWithTwo();
    }
}
