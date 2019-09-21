package com.demo.lock;

/**
 * 可重入粒度测试，调用父类的方法
 *
 * @author xiaol
 * @date 2019/9/21
 */
public class SynchronizedSuperClass12 {

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.doSomething();
    }

    public synchronized void doSomething() {
        System.out.println("我是父类方法");
    }

}

class TestClass extends SynchronizedSuperClass12 {
    @Override
    public synchronized void doSomething() {
        System.out.println("我是子类方法");
        super.doSomething();
    }
}