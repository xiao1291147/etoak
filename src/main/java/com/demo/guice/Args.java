package com.demo.guice;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.inject.BindingAnnotation;

/**
 * Args
 * @author xiao1
 * @date 2019/2/17
 */
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface Args {
}
