package com.java.programs.waitNotify;

import java.util.List;

public class Consumer implements Runnable {

    private final List<Integer> taskQueue;

    public Consumer(List<Integer> sharedQueue) {
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (counter < 10) {
            try {
                consume();
                counter++;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
    notify(): It wakes up one single thread that called wait() on the same object.
    It should be noted that calling notify() does not actually give up a lock on a resource.
    It tells a waiting thread that that thread can wake up.
    However, the lock is not actually given up until the notifier’s synchronized block has completed.

    So, if a notifier calls notify() on a resource but the notifier still needs to perform 10 seconds of actions on the
    resource within its synchronized block, the thread that had been waiting will need to wait at least another additional 10
    seconds for the notifier to release the lock on the object, even though notify() had been called.
     */
    private void consume() throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.isEmpty()) {
                System.out.println(
                    "Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }
            Thread.sleep(300);
            int i = (Integer)taskQueue.remove(0);
            System.out.println("Consumed: " + i);
            taskQueue.notifyAll();
        }
    }
}
