package MultiThread.MindMap.BarberShop;


//Actor - Customer, Barber
//Resource - Chair
//Rule -> chair empty - Occupied it, Customer will leave - full

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShopLock {



        private final int CHAIRS = 3;

        private final Lock lock = new ReentrantLock();
        private final Condition customerAvailable = lock.newCondition();
        private final Condition barberAvailable = lock.newCondition();

        private final Queue<Integer> waitingCustomers = new LinkedList<>();

        // Actor-1 : Barber
        class Barber implements Runnable {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        while (waitingCustomers.isEmpty()) {
                            System.out.println("Barber sleeping");
                            customerAvailable.await(); // sleep
                        }

                        int customer = waitingCustomers.poll();
                        System.out.println("Barber starts cutting hair of customer " + customer);

                        barberAvailable.signal(); // notify customer
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }

                    try {
                        Thread.sleep(1000); // haircut
                    } catch (InterruptedException e) {}
                }
            }
        }

        // Actor-2 : Customer
        class Customer implements Runnable {
            private final int id;

            Customer(int id) {
                this.id = id;
            }

            @Override
            public void run() {
                lock.lock();
                try {
                    if (waitingCustomers.size() == CHAIRS) {
                        System.out.println("Customer " + id + " left (no chairs)");
                        return;
                    }

                    waitingCustomers.add(id);
                    System.out.println("Customer " + id + " waiting");

                    customerAvailable.signal(); // wake barber

                    barberAvailable.await(); // wait for barber
                    System.out.println("Customer " + id + " getting haircut");

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        }

        public static void main(String[] args) {

            BarberShopLock shop = new BarberShopLock();

            new Thread(shop.new Barber()).start();

            for (int i = 1; i <= 10; i++) {
                new Thread(shop.new Customer(i)).start();
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                }
            }
        }
}
