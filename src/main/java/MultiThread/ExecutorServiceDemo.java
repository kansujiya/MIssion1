package MultiThread;

import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
//        int corePoolSize,
//        int maximumPoolSize,
//        long keepAliveTime,
//        TimeUnit unit,
//        BlockingQueue<Runnable> workQueue,
//        ThreadFactory threadFactory,
//        RejectedExecutionHandler handler
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2,
                4, 2,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),
                new CustomThreadFactory(),
                new customRejectedExecutionHandler());

        //executorService.allowCoreThreadTimeOut(true);
        for(int i =0; i < 8; i++) { //if i = 4 then all thread used by pool & queue
                                    //if i = 5 or 6 then one thread will be used by max thread pool
                                    //if i > 7 then pool is full, queue is full, max is full then
                                    // it will be rejected(2+2+2)
            executorService.submit(() -> {
                try{
                    Thread.sleep(5000);
                } catch (Exception e) {
                    //handle exceptions
                }
                System.out.println("task processed by: " + Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
    }
}

class CustomThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setPriority(Thread.NORM_PRIORITY);
        t.setDaemon(false);
        return t;
    }
}

class customRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task Rejected" + r.toString());
    }
}

