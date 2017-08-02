package com.etoak.test;

import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static com.etoak.util.Utils.cutUp;
import static com.etoak.util.Utils.sleep;
import static java.lang.System.out;

/**
 * TODO don't understand
 * Getting Started with Google Guava
 * Created by xiao1 on 2017/8/3.
 */
public class TestGuavaConcurrency {

    public static void main(String[] args) {
//        monitorExec();
//        listenableFutureExec();
//        futureCallbackExec();
//        asyncFunctionExec();

    }

    private static ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));

    static AsyncFunction<String, String> asyncFunction = new AsyncFunction<String, String>() {
        @Override
        public ListenableFuture<String> apply(String input) throws Exception {
            return listeningExecutorService.submit(() -> input + "101");
        }
    };

    static ListenableFuture<String> lis = listeningExecutorService.submit(() -> "张三");

    private static void asyncFunctionExec() {
        ListenableFuture<String> lf = Futures.transformAsync(lis, asyncFunction, listeningExecutorService);
        try {
            System.out.println(lf.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        listeningExecutorService.shutdown();
    }

    static class FutureCallbackImpl implements FutureCallback<String> {
        private StringBuilder builder = new StringBuilder("---");

        @Override
        public void onSuccess(String result) {
            builder.append(result).append(" successfully");
            System.out.println(result);
        }

        @Override
        public void onFailure(Throwable t) {
            builder.append(t.toString());
        }

        private String getCallbackResult() {
            return builder.toString();
        }
    }

    private static void futureCallbackExec() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(3));
        ListenableFuture<String> listenableFuture = executorService.submit(() -> {
            sleep(1000);
            return "Task completed";
        });
        FutureCallbackImpl futureCallback = new FutureCallbackImpl();
        Futures.addCallback(listenableFuture, futureCallback, executorService);
        System.out.println(futureCallback.getCallbackResult());

        sleep(3000);
        executorService.shutdown();
    }

    private static void listenableFutureExec() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(3));
        ListenableFuture<String> listenableFuture = executorService.submit(() -> {
            sleep(3000);
            return "finish";
        });

        listenableFuture.addListener(() -> {
            try {
                System.out.println("submit---" + listenableFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, executorService);
    }

    private static final int MAX_SIZE = 10;
    private List<String> list = new ArrayList<>();
    private Monitor monitor = new Monitor();
    private Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return list.size() < MAX_SIZE;
        }
    };

    private void addToList(String item) throws InterruptedException {
        monitor.enterWhen(listBelowCapacity);
        try {
            list.add(item);
            System.out.println("添加元素[" + item + "]成功, 当前size=" + list.size());
        } finally {
            monitor.leave();
        }
    }

    private void addToListSkipWait(String item) throws InterruptedException {
        boolean ok = monitor.tryEnterIf(listBelowCapacity);
        System.out.println("Thread[" + Thread.currentThread().getName() + "] item=" + item + ", 获取令牌：ok=" + ok);
        if (!ok) {
            return;
        }

        try {
            list.add(item);
            System.out.println("添加元素[" + item + "]成功, 当前size=" + list.size());
        } finally {
            monitor.leave();
        }
    }

    private static void monitorExec() {
        final TestGuavaConcurrency testGuava = new TestGuavaConcurrency();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 6; j++) {
                    try {
//                        testGuava.addToList(j + "--->" + Thread.currentThread().getName());
                        testGuava.addToListSkipWait(j + "--->" + Thread.currentThread().getName());
                        sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

        sleep(3000);
        cutUp();

        testGuava.list.forEach(out::println);
    }
}
