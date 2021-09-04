package com.java.programs.phaser;

import java.util.concurrent.Phaser;

/*
Phaser’s primary purpose is to enable synchronization of threads that represent one or more phases of activity. It lets us
define a synchronization object that waits until a specific phase has been completed. It then advances to the next phase
until that phase concludes. It can also be used to synchronize a single phase, and in that regard, it acts much like a
CyclicBarrier.
 */
public class PhaserExample {

    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        phaser.register();

        System.out.println("Starting");

        Thread t1 = new Thread(new MyThread(phaser, "A"));
        t1.start();
        Thread t2 = new Thread(new MyThread(phaser, "B"));
        t2.start();
        Thread t3 = new Thread(new MyThread(phaser, "C"));
        t3.start();

        int currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currentPhase + " Complete");

        // Wait for all threads to complete phase One.
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currentPhase + " Complete");

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currentPhase + " Complete");

        // Deregister the main thread.
        phaser.arriveAndDeregister();
        if (phaser.isTerminated()) {
            System.out.println("Phaser is terminated");
        }
    }

}
