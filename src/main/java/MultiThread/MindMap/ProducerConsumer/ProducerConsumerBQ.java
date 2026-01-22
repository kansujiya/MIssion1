package MultiThread.MindMap.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBQ {


    private static final BlockingQueue<Integer> queue =
            new ArrayBlockingQueue<>(5);

    public static class Producer implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 10; i++) {
                try {
                    queue.put(i);
                    System.out.println("produce: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    int value = queue.take();
                    System.out.println("Consumed: " + value);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}
