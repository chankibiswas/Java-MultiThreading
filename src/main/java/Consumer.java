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
