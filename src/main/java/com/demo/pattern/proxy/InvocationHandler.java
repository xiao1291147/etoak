package com.demo.pattern.proxy;

import java.lang.reflect.Method;

/**
 * InvocationHandler
 *
 * @author xiaol
 * @date 2019/9/15
 */
public interface InvocationHandler {

    void invoke(Object o, Method m);
}
