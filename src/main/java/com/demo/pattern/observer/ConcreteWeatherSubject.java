package com.demo.pattern.observer;

/**
 * 具体的目标对象，负责把有关状态存入到相应的观察者对象中
 *
 * @author xiaol
 * @date 2019/9/21
 */
public class ConcreteWeatherSubject extends WeatherSubject {

    /**
     * 获取天气的内容信息
     */
    private String weatherContent;

    @Override
    protected void notifyObservers() {
        // 循环所有注册的观察者
        for (Observer observer : observers) {
            // 规则是：女朋友需要 “下雨” 的条件通知，其他的条件不通知；老妈需要 “下雨” 或者 “下雪” 的条件通知，其他的条件不通知
            if (this.getWeatherContent().contains("下雨")) {
                if ("女朋友".equals(observer.getObserverName())) {
                    observer.pullUpdate(this);
                }
                if ("老妈".equals(observer.getObserverName())) {
                    observer.pullUpdate(this);
                }
            }
            if (this.getWeatherContent().contains("下雪")) {
                if ("老妈".equals(observer.getObserverName())) {
                    observer.pullUpdate(this);
                }
            }
        }
    }

    public String getWeatherContent() {
        return weatherContent;
    }

    public void setWeatherContent(String weatherContent) {
        this.weatherContent = weatherContent;
        // 内容有了，说明天气更新了，通知所有的订阅的人
        this.notifyObservers();
    }

    public void setWeatherContent2(String weatherContent) {
        this.weatherContent = weatherContent;
        // 内容有了，说明天气更新了，通知所有的订阅的人
        this.notifyObservers(weatherContent);
    }
}
