package com.demo.pattern.singleton;

/**
 * 单例模式Singleton
 * 应用场合：有些对象只需要一个就足够了，如古代皇帝，老婆
 * 作用：保证整个应用程序中某个实例有且只有一个
 * 类型：饿汉模式
 *
 * @author xiaol
 * @date 2019/9/14
 */
public class Singleton {


    /**
     * 1. 将构造方法私有化，不允许外部直接创建对象
     */
    private Singleton() {

    }


    /**
     * 2. 创建类的唯一实例，使用private static修饰
     */
    private static Singleton singleton = new Singleton();

    /**
     * 3. 提供一个用于获取实例的方法，使用public static修饰
     *
     * @return
     */
    public static Singleton getInstance() {
        return singleton;
    }
}
