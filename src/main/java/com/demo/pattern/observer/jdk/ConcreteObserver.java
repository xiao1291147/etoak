package com.demo.pattern.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 具体的观察者对象
 *
 * @author xiaol
 * @date 2019/9/21
 */
public class ConcreteObserver implements Observer {

    /**
     * 观察者名称
     */
    private String observerName;

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(Observable o, Object arg) {
        // 推的方式
        System.out.println(observerName + "收到了消息，目标推送过来的是" + arg);
        // 拉的方式
        System.out.println(observerName + "收到了信息，主动到目标对象中去拉，拉的内容是" + ((ConcreteWetherSubject) o).getContent());
    }
}
