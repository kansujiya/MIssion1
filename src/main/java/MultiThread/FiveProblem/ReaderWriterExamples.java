package MultiThread.FiveProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriterExamples {

    public static class SharedData  {

        int value = 0;

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        int read() {
            lock.readLock().lock();
            try {return value;} finally {
                lock.readLock().unlock();
            }
        }

        void write(int val) {
            lock.writeLock().lock();
            try {
                value = val;
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        SharedData data = new SharedData();
        ExecutorService ex = Executors.newFixedThreadPool(5);

        // readers
        for (int i = 0; i < 4; i++) {
            ex.submit(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName() + " read " + data.read());
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        ex.submit(() -> {
            try {
                for (int v=1; v<=5; v++){
                    data.write(v);
                    System.out.println("Writer wrote " + v);
                    Thread.sleep(350);
                }
            } catch (InterruptedException e){ Thread.currentThread().interrupt(); }
        });

        ex.shutdown();
    }
}
