package com.demo.pattern.observer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.google.common.collect.Maps;

/**
 * 测试类
 *
 * @author xiaol
 * @date 2019/9/21
 */
public class Client {

    public static void main(String[] args) {
        // 1.创建目标
        ConcreteWeatherSubject wether = new ConcreteWeatherSubject();

        // 2.创建观察者
        ConcreteObserver observerGirl = new ConcreteObserver();
        observerGirl.setObserverName("女朋友");
        observerGirl.setRemindThing("下雨了，安静的呆在家里吧");

        ConcreteObserver observerMum = new ConcreteObserver();
        observerMum.setObserverName("老妈");
        observerMum.setRemindThing("不管下雨还是下雪，我都不出门了");

        // 3. 注册观察者
        wether.attach(observerGirl);
        wether.attach(observerMum);

        // 4. 目标发布天气
        HashMap<String, String> map = Maps.newHashMapWithExpectedSize(3);
        LocalDate now = LocalDate.now();
        map.put(now.format(DateTimeFormatter.BASIC_ISO_DATE), "下雨");
        map.put(now.plusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE), "下雪");
        map.put(now.plusDays(2).format(DateTimeFormatter.BASIC_ISO_DATE), "明天天气晴朗，蓝天白云，气温28度");
        map.forEach((k, v) -> wether.setWeatherContent(k + v));
    }
}
