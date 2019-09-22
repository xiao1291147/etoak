package com.demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * com.demo.annotation.ParseAnno
 *
 * @author xiaol
 * @date 2019/9/22
 */
public class ParseAnno {

    public static void main(String[] args) {
        try {
            // 1. 使用类加载器加载类
            Class<?> c = Class.forName("com.demo.annotation.Child");
            // 2. 找到类上面的注解
            boolean exist = c.isAnnotationPresent(Description.class);
            if (exist) {
                // 3. 拿到注解实例
                Description annotation = c.getAnnotation(Description.class);
                System.out.println(annotation.value());
            }

            // 4. 找到方法上的注解
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                boolean methodExist = method.isAnnotationPresent(Description.class);
                if (methodExist) {
                    Description methodAnnotation = method.getAnnotation(Description.class);
                    System.out.println(methodAnnotation.value());
                }
            }

            // 另外一种解析方法
            for (Method method : methods) {
                for (Annotation annotation : method.getAnnotations()) {
                    Description description;
                    if (annotation instanceof Description) {
                        description = (Description) annotation;
                        System.out.println(description.value());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
