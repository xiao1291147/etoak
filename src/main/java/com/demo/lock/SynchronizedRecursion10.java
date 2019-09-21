package com.demo.lock;

/**
 * 可重入粒度测试：递归调用本方法
 *
 * @author xiaol
 * @date 2019/9/21
 */
public class SynchronizedRecursion10 {

    private int a = 0;

    public static void main(String[] args) {
        SynchronizedRecursion10 synchronizedRecursion10 = new SynchronizedRecursion10();
        synchronizedRecursion10.method1();
    }

    private synchronized void method1() {
        System.out.println("这是method1，a = " + a);
        if (a == 0) {
            a++;
            method1();
        }
    }
}
