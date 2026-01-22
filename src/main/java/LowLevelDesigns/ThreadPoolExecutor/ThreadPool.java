package LowLevelDesigns.ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private final TaskQueue queue;
    private final List<Worker> workers = new ArrayList<>();

    public ThreadPool(int poolSize, int queueCapacity) {
        this.queue = new TaskQueue(queueCapacity);

        for(int i = 0; i < poolSize; i++) {
            Worker w = new Worker(queue, i);
            workers.add(w);
            w.start();
        }
    }

    public void submit(Runnable task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() throws InterruptedException {
        for (Worker w : workers) {
            w.shutDown();
        }
    }
}
