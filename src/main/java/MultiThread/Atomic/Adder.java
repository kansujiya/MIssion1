package MultiThread.Atomic;

import java.util.concurrent.locks.Lock;

public class Adder implements Runnable {

    Count count;
    Lock lock;

    public Adder(Count c, Lock lock) {
        this.count = c;
        this.lock = lock;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            lock.lock();


            if (i >= 50) {
                System.out.println("STOP");
            }
            count.value++;
            System.out.println("Add");
            System.out.println(count.value);
            lock.unlock();
        }
    }
}
