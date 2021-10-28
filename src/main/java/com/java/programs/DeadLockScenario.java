package com.java.programs;

public class DeadLockScenario {

    private int counter;

    private Object o1;
    private Object o2;

    public DeadLockScenario() {
        o1 = new Object();
        o2 = new Object();
    }

    public static void main(String[] st) {
        DeadLockScenario s = new DeadLockScenario();
        Thread t1 = new Thread(() -> {
            try {
                s.increamentCount();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                s.increamentCountAndGet();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        t1.setName("T1");
        t2.setName("T2");
        t1.start();
        t2.start();

    }

    public void increamentCount() throws Exception {   // Thread 1
        synchronized (o1) {
            Thread.sleep(5000);
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName());
                counter++;
            }
        }
    }

    public int increamentCountAndGet() throws Exception {  /// Thread 2
        synchronized (o2) {
            Thread.sleep(5000);
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName());
                return ++counter;
            }
        }
    }
}
