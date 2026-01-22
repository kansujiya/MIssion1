package MultiThread.FiveProblem;

import java.util.concurrent.*;

public class ProducerConsumerBlockingQueue {

    public static void main(String args[]) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable producer = () -> {
            try {
                for(int i = 0; i<20; i++) {
                    queue.put(i);
                    System.out.println("Produced" + i);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        Runnable consumer = () -> {
            try {
                while(true) {
                    Integer x = queue.poll();
                    System.out.println("Consumed" + x);
                    Thread.sleep(150);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        executor.submit(producer);
        executor.submit(consumer);

        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            executor.shutdownNow();
        }, 5, TimeUnit.SECONDS);
    }
}

