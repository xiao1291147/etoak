package com.demo.pattern.factory;

/**
 * 人物的实现接口测试类
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class PersonTest {

    public static void main(String[] args) {
        PersonFactory mcFactory = new MCFactory();
        Girl girl = mcFactory.getGirl();
        girl.drawWomen();

        System.out.println("############################");
        PersonFactory hnFactory = new HNFactory();
        Boy boy = hnFactory.getBoy();
        boy.drawMan();
    }
}
