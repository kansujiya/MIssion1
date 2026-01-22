package threads.BlockingQueue;

public class Consumer implements Runnable {
    BlockingQueue<Number> blockingQueue = null;

    public Consumer(BlockingQueue<Number> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                Integer value = blockingQueue.consume();
                System.out.println("Consumed: " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
