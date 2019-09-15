package com.demo.pattern.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TimeHandler
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class TimeHandler implements InvocationHandler {

    private final Object target;

    public TimeHandler(Object target) {
        this.target = target;
    }

    @Override
    public void invoke(Object o, Method m) {
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("汽车开始行驶...");
            m.invoke(target);
            System.out.println("汽车结束行驶... 汽车行驶时间：" + (System.currentTimeMillis() - startTime) + "毫秒！");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
