package MultiThread.Youtube.Locks.ReadWriteLock;

import MultiThread.Youtube.Locks.ReadWriteLock.SharedResource;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    public static void main(String[] args) {

        SharedResource sharedResource1= new SharedResource();
        SharedResource sharedResource2= new SharedResource();

        ReadWriteLock lock = new ReentrantReadWriteLock();

        Thread write = new Thread(() -> {
            sharedResource1.write(lock); //shared lock
        });

        Thread read1 = new Thread(() -> {
            sharedResource2.read(lock); ////shared lock
        });

        Thread read2 = new Thread(() -> {
            sharedResource2.read(lock); //exclusive lock
        });

        read1.start();
        read2.start();
        write.start();

    }

}
