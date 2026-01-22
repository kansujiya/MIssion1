package threads.SleepingBarber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Simulator {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> customerQueue = new LinkedBlockingQueue<>(10);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new Barber(customerQueue));
        executorService.submit(new CustomerProducer(customerQueue));
    }
}
