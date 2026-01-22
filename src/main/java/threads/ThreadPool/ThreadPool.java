package threads.ThreadPool;

import java.util.concurrent.LinkedBlockingQueue;
/*
ThreadPool should be always used for short running jobs
 */

public class ThreadPool {
    private int queueCapacity =  0;
    private Thread[] workers = null;
    private boolean isClosed = false;
    private LinkedBlockingQueue<Runnable> taskQueue;

    public ThreadPool(int poolSize, LinkedBlockingQueue taskQueue, int queueCapacity){
        this.taskQueue = taskQueue;
        this.queueCapacity = queueCapacity;
        this.workers = new Thread[poolSize];
        for(int i=0; i<workers.length; i++){
            workers[i] = new Thread(new Worker());
            workers[i].start();
        }
    }

    public void execute(Runnable runnable) throws InterruptedException {
        if(taskQueue.size() >= this.queueCapacity) {
            throw new RuntimeException("Pool is full");
        }
        taskQueue.add(runnable);
    }

    public void shutDown(){
        isClosed=true;
    }

    private class Worker implements Runnable{
        @Override
        public void run() {
            while(!isClosed) {
                try {
                    if(!taskQueue.isEmpty()){
                        Runnable task = taskQueue.poll();
                        if(task != null) task.run();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
