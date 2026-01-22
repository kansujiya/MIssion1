package LowLevelDesigns.ThreadPoolExecutor;

public class Worker extends Thread {

    private final TaskQueue taskQueue;
    private volatile boolean running = true; //Guarantees visibility of variable changes.

    public Worker(TaskQueue taskQueue, int id) {
        super("Worker" + id);
        this.taskQueue = taskQueue;
    }

    public void shutDown() throws InterruptedException {
        running = false;
        this.interrupt(); // wake up if blocked
    }

    @Override
    public void run() {
        while (running) {
            try {
                Runnable task = taskQueue.Take();
                task.run();
            } catch (InterruptedException e) {
                if (!running) break;
            }
        }
    }
}
