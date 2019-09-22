package com.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.demo.annotation.Description
 *
 * @author xiaol
 * @date 2019/9/22
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
// 只对类有效
@Inherited
@Documented
public @interface Description {

    String value();
}
