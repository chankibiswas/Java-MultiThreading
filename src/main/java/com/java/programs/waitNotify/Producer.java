package com.java.programs.waitNotify;

import java.util.List;

public class Producer implements Runnable {

    private final List<Integer> taskQueue;
    private final int MAX_CAPACITY;

    public Producer(List<Integer> sharedQueue, int size) {
        this.taskQueue = sharedQueue;
        this.MAX_CAPACITY = size;
    }

    @Override
    public void run() {
        int counter = 0;
        while (counter < 10) {
            try {
                produce(counter++);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
    wait(): It tells the calling thread to give up the lock and go to sleep until some other thread enters the same
    monitor and calls notify().
    The wait() method releases the lock prior to waiting and reacquires the lock prior to
    returning from the wait() method.
    The wait() method is actually tightly integrated with the synchronization lock, using a feature not available directly
    from the synchronization mechanism.

    In other words, it is not possible for us to implement the wait() method purely in Java. It is a native method.
     */
    private void produce(int i) throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.size() == MAX_CAPACITY) {
                System.out.println(
                    "Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }

            Thread.sleep(100);
            taskQueue.add(i);
            System.out.println("Produced: " + i);
            taskQueue.notifyAll();
        }
    }
}
