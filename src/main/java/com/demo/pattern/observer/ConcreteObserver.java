package com.demo.pattern.observer;

/**
 * 具体的观察者对象，实现更新的方法，使自身的状态和目标的状态保持一致
 *
 * @author xiaol
 * @date 2019/9/21
 */
public class ConcreteObserver implements Observer {

    /**
     * 观察者的名字，是谁收到了这个讯息
     */
    private String observerName;
    /**
     * 天气内容的情况，这个消息从目标处获取
     */
    private String wetherContent;
    /**
     * 提醒的内容
     */
    private String remindThing;

    /**
     * 获取目标类的状态同步到观察者的状态中
     *
     * @param weatherSubject
     */
    @Override
    public void pullUpdate(WeatherSubject weatherSubject) {
        this.wetherContent = ((ConcreteWeatherSubject) weatherSubject).getWeatherContent();
        System.out.println(observerName + "收到了" + wetherContent + "， " + remindThing);
    }

    @Override
    public void pushUpdate(String content) {
        this.wetherContent = content;
        System.out.println(observerName + "收到了" + wetherContent + "， " + remindThing);
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    @Override
    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }

    public String getWetherContent() {
        return wetherContent;
    }

    public void setWetherContent(String wetherContent) {
        this.wetherContent = wetherContent;
    }

    public String getRemindThing() {
        return remindThing;
    }

    public void setRemindThing(String remindThing) {
        this.remindThing = remindThing;
    }
}
