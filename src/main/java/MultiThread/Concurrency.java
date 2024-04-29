package MultiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class Barber {
    public void cutHair() {
        System.out.println("Barber: Cutting Hair --- " + Thread.currentThread().getName());
    }
}

class Customer {

    public void enter() {
        System.out.println("Customer: Enters the shop --- " + Thread.currentThread().getName());
    }

    public void getHairCut() {
        System.out.println("Customer: Getting Haircut --- " + Thread.currentThread().getName());
    }

    public void leave() {
        System.out.println("Customer: Leaves the shop --- " + Thread.currentThread().getName());
    }
}

//limit=4									# limit for total number of customer
//customers = 0						# customer counter
//mutex = new ReenterantLock()		# mutex to protect customer counter
//customerReady = new Semaphore(0)		# barber waits on customerReady
//barberReady = new Semaphore(0)		# customer waits on barberReady
//customerDone = new Semaphore(0)		# customer signals haircut is done
//barberDone = new Semaphore(0)		# barber signals haircut is done
public class Concurrency {
    private static final int NUM_ITERATION = 8;

    public static void main(String[] args) {

        ExecutorService executor = Executors.newWorkStealingPool();
        Barbershop barbershop = new Barbershop(4);


        Callable<Void> barbershopTask = barbershop::startService;
        Callable<Void> receiveCustomerTask = barbershop::receiveNewCustomer;

        List<Future<Void>> barberFutures = new ArrayList<>();
        List <Future<Void>> customerFutures = new ArrayList<>();


        for (int i=0; i<NUM_ITERATION; i++) {
            Future <Void> barberFuture = executor.submit(barbershopTask);
            barberFutures.add(barberFuture);

            Future <Void> customerFuture = executor.submit(receiveCustomerTask);
            customerFutures.add(customerFuture);
        }

        barberFutures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        customerFutures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

    }
}

// flow
// Actor HAS IS
// SoliD + cr

class Barbershop {

    private final int limit;
    public int customers;
    private final Barber barber;

    private final Semaphore customerReady;
    private final Semaphore barberReady;

    private final Semaphore customerDone;
    private final Semaphore barberDone;

    private final ReentrantLock mutex;

    public Barbershop(int limit) {
        this.limit = limit;
        customers = 0;

        customerReady = new Semaphore(0);
        barberReady = new Semaphore(0);

        customerDone = new Semaphore(0);
        barberDone = new Semaphore(0);

        mutex = new ReentrantLock();
        barber = new Barber();
    }

    public Void startService() {

        // wait for a customer to arrive
        try {
            customerReady.acquire();
        } catch (InterruptedException e) {
            System.out.println("Barber wait task is interrupted: " + e.getMessage());
        }

        // signal that barber is ready
        barberReady.release();

        // give a haircut
        barber.cutHair();


        // wait for a customer to approve done
        try {
            customerDone.acquire();
        } catch (InterruptedException e) {
            System.out.println("Barber wait task is interrupted: " + e.getMessage());
        }
        // signal the customer that barber is done
        barberDone.release();
        System.out.println("Haircut is done");
        return null;
    }

    public Void receiveNewCustomer() {

        Customer customer = new Customer();

        customer.enter();

        mutex.lock();
        if (customers == limit) {
            mutex.unlock();
            customer.leave();
            return null;
        }
        customers += 1;
        mutex.unlock();

        customerReady.release();

        // wait for the barber to be available
        try {
            barberReady.acquire();
        } catch (InterruptedException e) {
            System.out.println("Customer wait task is interrupted: " + e.getMessage());
        }

        // get the haircut
        customer.getHairCut();

        // signal that customer is done
        customerDone.release();

        // wait for barber to approve done
        try {
            barberDone.acquire();
        } catch (InterruptedException e) {
            System.out.println("Customer wait task is interrupted: " + e.getMessage());
        }

        mutex.lock();
        customers -= 1;
        mutex.unlock();

        return null;
    }

}

