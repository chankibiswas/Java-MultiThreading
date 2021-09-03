package com.java.programs.waitNotify;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerExampleWithWaitNotify {

    public static void main(String[] s) {
        List<Integer> taskQueue = new ArrayList<>();
        int MAX_CAPACITY = 5;
        Thread tProducer = new Thread(new Producer(taskQueue, MAX_CAPACITY), "com.java.programs.waitNotify.Producer");
        Thread tConsumer = new Thread(new Consumer(taskQueue), "com.java.programs.waitNotify.Consumer");
        tProducer.start();
        tConsumer.start();
    }
}
