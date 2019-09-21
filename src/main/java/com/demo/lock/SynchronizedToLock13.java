package com.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两种形式的锁
 *
 * @author xiaol
 * @date 2019/9/21
 */
public class SynchronizedToLock13 {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        SynchronizedToLock13 synchronizedToLock13 = new SynchronizedToLock13();
        synchronizedToLock13.method1();
        synchronizedToLock13.method2();
    }

    private synchronized void method1() {
        System.out.println("我是synchronized形式的锁");
    }

    private void method2() {
        lock.lock();
        try {
            System.out.println("我是lock形式的锁");
        } finally {
            lock.unlock();
        }
    }
}
