package MultiThread.Atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        Count c = new Count(0);

        Lock lock = new ReentrantLock();

        Adder add = new Adder(c, lock);
        Subtract sub = new Subtract(c, lock);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(add);
        executor.submit(sub);

        System.out.println(c.value);
        executor.awaitTermination(100L, TimeUnit.MINUTES);
        System.out.println(c.value);
    }
}
