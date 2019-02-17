package com.demo.guice.helloworlddemo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.inject.BindingAnnotation;

/**
 * Output
 * @author xiao1
 * @date 2019/2/17
 */
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface Output {
}
