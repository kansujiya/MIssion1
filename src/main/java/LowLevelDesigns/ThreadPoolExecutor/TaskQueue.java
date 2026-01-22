package LowLevelDesigns.ThreadPoolExecutor;

import threads.BlockingQueue.BlockingQueue;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {

    private final Queue<Runnable> queue = new LinkedList<>();

    private final int capcity;


    public TaskQueue(int capcity) {
        this.capcity = capcity;
    }

    public synchronized void put(Runnable task) throws InterruptedException {
        while (queue.size() == capcity) {
            wait();
        }
        queue.add(task);
        notifyAll();
    }

    public synchronized Runnable Take() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();// wait until next a task arrive
        }

        Runnable task = queue.poll();
        notifyAll();
        return task;
    }
}
