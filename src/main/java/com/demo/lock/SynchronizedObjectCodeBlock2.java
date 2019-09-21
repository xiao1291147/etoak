package com.demo.lock;

import java.util.concurrent.TimeUnit;

/**
 * 对象所示例1，代码块形式
 *
 * @author xiaol
 * @date 2019/9/20
 */
public class SynchronizedObjectCodeBlock2 implements Runnable {

    private static SynchronizedObjectCodeBlock2 instance = new SynchronizedObjectCodeBlock2();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        }
        System.out.println("finished");
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("我是lock1。我叫" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " lock1运行结束。");
        }
    }
}
