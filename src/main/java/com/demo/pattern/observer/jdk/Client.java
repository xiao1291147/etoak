package com.demo.pattern.observer.jdk;

/**
 * jdk观察者测试类
 *
 * @author xiaol
 * @date 2019/9/21
 */
public class Client {

    public static void main(String[] args) {
        // 创建天气作为一个目标，也可以说是观察者
        ConcreteWetherSubject subject = new ConcreteWetherSubject();

        // 创建女朋友作为观察者
        ConcreteObserver girl = new ConcreteObserver();
        girl.setObserverName("女朋友");
        // 创建老妈作为观察者
        ConcreteObserver mum = new ConcreteObserver();
        mum.setObserverName("老妈");

        // 注册观察者
        subject.addObserver(girl);
        subject.addObserver(mum);

        // 目标更新天气情况
        subject.setContent("天气晴，气温28度");
    }
}
