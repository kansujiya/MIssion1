package threads.ThreadPool;

import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class Simulator {
    public static void main(String[] args) throws InterruptedException {
        int poolSize = 3;
        int queueCapacity = 4;
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        ThreadPool threadPool = new ThreadPool(poolSize, queue, queueCapacity);

        for (int i = 0; i < 10; i++) {
            Runnable runnable = createRunnable();

            //Check queue size before submitting
            while (queue.size() >= queueCapacity) {
                TimeUnit.MICROSECONDS.sleep(100);
            }

            threadPool.execute(runnable);
        }

        TimeUnit.SECONDS.sleep(10);
        threadPool.shutDown();
    }

    private static Runnable createRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                String uid = UUID.randomUUID().toString();
                //while (true) {
                    try {
                        System.out.println("In Thread: "
                                +Thread.currentThread().getId() +" rid: "+ uid);
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                //}
            }
        };
    }
}
