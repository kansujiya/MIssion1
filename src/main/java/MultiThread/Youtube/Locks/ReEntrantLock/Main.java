package MultiThread.Youtube.Locks.ReEntrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource1 = new SharedResource();
        ReentrantLock lock = new ReentrantLock();


        Thread t1 = new Thread(()-> {
            sharedResource1.producer(lock);
        });

        SharedResource sharedResource2 = new SharedResource();
        Thread t2 = new Thread(()-> {
            sharedResource2.producer(lock);
        });

        t1.start();
        t2.start();
    }
}
