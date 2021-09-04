package com.java.programs.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskExecutor {

    public static void main(String[] s) {
        //ExecutorService executorService = Executors.newFixedThreadPool(1000);
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<String>> listOfCallable = new ArrayList<>();
        listOfCallable.add(new DummyCallable(1));
        listOfCallable.add(new DummyCallable(2));
        listOfCallable.add(new DummyCallable(3));

        try {
            List<Future<String>> futures = executorService.invokeAll(listOfCallable);
            for (Future<String> f : futures) {
                System.out.println(f.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

}
