package com.howard.juc.future;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Created by howard on 16/7/5.
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
//        CompletableFuture<String> future = new CompletableFuture<>();
//        future.complete("42");
//        future.thenAccept(System.out::println).thenAccept(v -> System.out.println("done"));


        ExecutorService pool = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            return "done";
        }, pool);
        future.thenApply(s -> {
            System.out.println("Thread name: " + Thread.currentThread().getName() + ", result: " + s);
            return s.length();
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        try {
            pool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future.thenApply(s -> {
            System.out.println("Thread name: " + Thread.currentThread().getName() + ", Second transformation");
            return s.length();
        });
    }
}
