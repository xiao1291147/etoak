package com.etoak.test;

import java.util.concurrent.TimeUnit;

/**
 * Effective Java
 * Created by xiaoliang.cui_c on 2017/7/26.
 */
public class TestConcurrency {
    private static boolean stopRequested;
    private static volatile boolean stopRequested2;

    public static void main(String[] args) throws InterruptedException {
//        testConcurr1();
//        testConcurr2();
        testConcurr3();
    }

    /**
     * 你可能期待这个程序运行大约一秒钟左右，之后主线程将stopRequested设置为true，只是后台线程的循环终止。
     *      但是在我的机器上，这个程序永远不会终止：因为后台线程永远在循环！
     * 问题在于，由于没有同步，就不能保证后台线程何时 “看到” 主线程对stopRequested的值所做的改变。没有同步，虚拟机将这个代码：
     *  while (!done)
     *      i++;
     * 转变成这样：
     *  if (!done)
     *      while (true)
     *          i++;
     *
     * @throws InterruptedException
     */
    private static void testConcurr1() throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
            System.out.println("backgroundThread stop, i=" + i);
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }

    /**
     * 注意写方法(requestStop)和读方法(stopRequested)都被同步了。
     *      只同步写方法还不够！实际上，如果读和写操作没有都被同步，同步就不会起作用。
     *
     * @throws InterruptedException
     */
    private static void testConcurr2() throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested()) {
                i++;
            }
            System.out.println("backgroundThread stop, i=" + i);
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    /**
     * StopThread中被同步的方法的动作几十没有同步也是原子的。换句话说，这些方法的同步只是为了它的通信效果，而不是为了互斥访问。
     *      虽然循环的每个迭代中的同步开销很小，还是有其他更正确的替代方法，它更简洁，性能也可能更好。
     * 如果stopRequested2被声明为volatile，第二种版本的StopThread中的锁就可以省略。
     *      虽然volatile修饰符不执行互斥访问，但它可以保证任何一个线程在读取该域的时候都将看到最近刚刚被写入的值。
     * 在使用volatile的时候务必小心。++操作符不是原子的。它在nextSerialNumber域中执行两项操作：
     *      首先它读取值，然会写回一个新值，相当于原来的值再加上1.如果第二个线程在第一个线程读取旧值和写回新值期间读取这个域，
     *      第二个线程就会与第一个线程一起看到同一个值，并返回相同的序列号。
     *      这就是安全性失败：这个程序会计算出错误的结果。
     * 修正generateSerialNumber方法的一种方法是在它的声明中增加synchronized修饰符，这么做就可以删除volatile修饰符。
     *      或者使用java.util.concurrent.atomic.AtomicLong
     *  private static volatile int nextSerialNumber = 0;
     *  public static int generateSerialNumber() {
     *      return nextSerialNumber++;
     *  }
     * @throws InterruptedException
     */
    private static void testConcurr3() throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested2) {
                i++;
            }
            System.out.println("backgroundThread stop, i=" + i);
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested2 = true;
    }
}
