package MultiThread.Youtube.Locks.ReEntrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    boolean isActive = false;

    public void producer(ReentrantLock lock) {
        lock.lock();

        System.out.println("Lock acquired by: " + Thread.currentThread().getName());
        isActive = true;
        try{
            Thread.sleep(4000);
        }catch (Exception e) {}
        finally {
            lock.unlock();
            System.out.println("lock released by: " + Thread.currentThread().getName());
        }
    }

}
