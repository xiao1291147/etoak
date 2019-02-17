package com.demo.guice.server.impl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.inject.BindingAnnotation;

/**
 * SessionId
 * @author xiao1
 * @date 2019/2/17
 */
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface SessionId {
}
