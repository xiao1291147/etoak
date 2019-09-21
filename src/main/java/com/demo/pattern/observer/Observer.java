package com.demo.pattern.observer;

/**
 * 这是一个观察者接口，定义一个更新的接口给那些在目标发生改变的时候被通知的对象
 *
 * @author xiaol
 * @date 2019/9/21
 */
public interface Observer {

    /**
     * 更新的接口，拉模式
     *
     * @param weatherSubject
     */
    void pullUpdate(WeatherSubject weatherSubject);

    /**
     * 更新的接口，推模式
     *
     * @param content
     */
    void pushUpdate(String content);

    /**
     * 设置观察者名称
     *
     * @param observerName
     */
    void setObserverName(String observerName);

    /**
     * 取得观察者名称
     *
     * @return
     */
    String getObserverName();
}
