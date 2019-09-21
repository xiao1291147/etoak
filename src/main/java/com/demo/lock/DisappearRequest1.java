package com.demo.lock;

/**
 * 消失的请求数
 *
 * @author xiaol
 * @date 2019/9/20
 */
public class DisappearRequest1 implements Runnable {

    private static DisappearRequest1 instance = new DisappearRequest1();
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }
}
