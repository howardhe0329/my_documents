package com.howard.guava.concurrent;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by howard on 16/7/6.
 */
public class ListenableFutureDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(service);
        ListenableFuture<String> future = listeningExecutorService.submit(new Task());
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.format("Thread name: %s, Execute onSuccess. result: %s \n", Thread.currentThread().getName(), result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.format("Thread name: %s, Execute onFailure. \n", Thread.currentThread().getName());
                System.out.println(t);
            }
        });
        System.out.format("Thread name: %s, shutdown\n", Thread.currentThread().getName());
        listeningExecutorService.shutdownNow();
        try {
            listeningExecutorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.format("Thread name: %s, Execute Task. \n", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            return "hello";
        }
    }
}
