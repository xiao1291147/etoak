package com.demo.guice.server.impl;

/**
 * LoggingInterceptor
 * @author xiao1
 * @date 2019/2/17
 */

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.common.base.Joiner;
import javax.inject.Inject;
import javax.inject.Provider;

public class LoggingInterceptor implements MethodInterceptor {

    @Inject
    @SessionId
    private Provider<Long> sessionIdProvider;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println(String.format("In session %d: Calling %s#%s(%s)", sessionIdProvider.get(), method.getDeclaringClass(), method.getName(),
                Joiner.on(",").join(invocation.getArguments())));
        return invocation.proceed();
    }
}