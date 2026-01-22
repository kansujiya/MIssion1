package threads.SleepingBarber;


import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Barber implements Runnable{
    private LinkedBlockingQueue<Integer> customerQueue = null;
    Random random = new Random();
    public Barber(LinkedBlockingQueue<Integer> blockingQueue){
        this.customerQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                  Integer customer = customerQueue.take();
                  System.out.println("Barber taken the customer : " + customer );
                  Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
