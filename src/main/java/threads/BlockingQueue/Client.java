package threads.BlockingQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {
        BlockingQueue<Number> blockingQueue = new BlockingQueue<Number>(10);
        Consumer consumer = new Consumer(blockingQueue);
        Producer producer = new Producer(blockingQueue);

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for(int i=0; i<3; i++){
            executorService.submit(producer);
            executorService.submit(consumer);
        }
    }
}
