package threads.SleepingBarber;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomerProducer implements  Runnable{
    private LinkedBlockingQueue<Integer> customerQueue = null;
    Random random = new Random();
    public  CustomerProducer(LinkedBlockingQueue<Integer> linkedBlockingQueue){
        this.customerQueue = linkedBlockingQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                customerQueue.put(random.nextInt(100));
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
