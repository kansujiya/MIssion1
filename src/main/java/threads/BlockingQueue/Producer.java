package threads.BlockingQueue;

import java.util.Random;

public class Producer implements Runnable{
    BlockingQueue<Number> blockingQueue = null;
    Random random = new Random();
    public Producer(BlockingQueue<Number> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                Integer value = random.nextInt(1000);
                blockingQueue.produce(value);
                System.out.println("Produced: " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
