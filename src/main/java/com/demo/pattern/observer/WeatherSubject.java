package com.demo.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标对象，它知道观察它的观察者，并提供注册（添加）和删除观察者的接口
 *
 * @author xiaol
 * @date 2019/9/21
 */
public abstract class WeatherSubject {

    /**
     * 用来保存注册的观察者对象
     */
    protected List<Observer> observers = new ArrayList<>();

    /**
     * 把订阅天气的人添加到订阅者列表中
     *
     * @param observer
     */
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * 删除集合中的指定订阅天气的观察者
     *
     * @param observer
     */
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * 通知所有已经订阅天气的观察者对象
     */
    protected void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.pullUpdate(this);
        }
    }

    /**
     * 通知所有已经订阅天气的观察者对象
     */
    protected void notifyObservers(String content) {
        for (Observer observer : this.observers) {
            observer.pushUpdate(content);
        }
    }
}
