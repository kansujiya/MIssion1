package MultiThread.MindMap.BarberShop;

import javax.sound.midi.ShortMessage;
import java.util.concurrent.Semaphore;

public class BarberShopSemaphore {

    private static final int CHAIRS = 3;

    static Semaphore customer = new Semaphore(0);
    static Semaphore barber = new Semaphore(0);
    static Semaphore chair = new Semaphore(1);


    //acquire() → try to take a permit (may block)
    //release() → return a permit (may wake a thread)

    static int currentChair = CHAIRS;

    public static class Barber implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    customer.acquire(); //wait for customer
                    chair.acquire(); //protect chair

                    currentChair++;

                    barber.release();
                    chair.release();

                    System.out.println("Barber is cutting hair");
                    Thread.sleep(1000);         // haircut time

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static class Customer implements Runnable {
        private final int id;

        Customer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                chair.acquire(); // try to get seat

                if(currentChair > 0) {
                    currentChair--;
                    System.out.println("Customer " + id + " waiting");

                    customer.release(); // notify barber
                    chair.release();

                    barber.acquire();            // wait for barber
                    System.out.println("Customer " + id + " getting haircut");
                } else {
                    chair.release();
                    System.out.println("Customer " + id + " left (no chairs)");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Barber()).start();

        // Create customers
        for (int i = 1; i <= 10; i++) {
            new Thread(new Customer(i)).start();
            try {
                Thread.sleep(300); // customers arrive randomly
            } catch (Exception e) {}
        }

    }
}
