package MultiThread.MindMap.ProducerConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*;


public class ProducerConsumerWithExecutor {

    // Shared buffer (RESOURCE)
    private static final BlockingQueue<Integer> buffer =
            new ArrayBlockingQueue<>(1);

    // Producer (ACTOR)
    static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.put(i); // blocks if buffer full
                    System.out.println("Produced: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Consumer (ACTOR)
    static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while(!buffer.isEmpty()) {
                    int value = buffer.take(); // blocks if buffer empty
                    System.out.println("Consumed: " + value);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {

        // Thread manager (RULE: performance + control)
        ExecutorService executor =
                new ThreadPoolExecutor(
                        4,                      // core threads
                        5,                      // max threads
                        0L,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>()
                );

        // SAME PRODUCER–CONSUMER, just submitted as tasks
        executor.execute(new Producer());
        executor.execute(new Producer());
        executor.execute(new Consumer());

        executor.shutdown();
    }
}
