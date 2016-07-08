package com.howard.juc.future;

import java.util.concurrent.*;

/**
 * Created by howard on 16/7/4.
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(new Task());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(5000);
            return "success";
        }
    }
}
