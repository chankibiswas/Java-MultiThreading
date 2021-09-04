package com.java.programs.phaser;

import java.util.concurrent.Phaser;

class MyThread implements Runnable {

    private Phaser phaser;
    private String name;

    public MyThread(Phaser phaser, String title) {
        this.phaser = phaser;
        this.name = title;
        phaser.register();
    }

    @Override
    public void run() {
        System.out.println("Thread: " + name + " Phase Zero Started");
        phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Thread: " + name + " Phase One Started");
        phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Thread: " + name + " Phase Two Started");
        phaser.arriveAndDeregister();
    }
}

