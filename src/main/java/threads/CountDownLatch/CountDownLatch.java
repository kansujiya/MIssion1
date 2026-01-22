package threads.CountDownLatch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatch {
    private int count = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition countZero =  lock.newCondition();

    public  CountDownLatch(int count){
        this.count = count;
    }

    public void decrementCount(){
        try{
            System.out.println("Taking Lock " + Thread.currentThread().getId());
            lock.lock();
            count--;
            if(count == 0){
                countZero.signalAll();
            }
        }finally {
            lock.unlock();
            System.out.println("Released Lock: " + Thread.currentThread().getId());
        }
    }

    public void waitForZero() throws InterruptedException {
        try{
            lock.lock();
            while(count != 0){
                countZero.await();
            }
            System.out.println("Achived " + Thread.currentThread().getId());
        }finally {
            lock.unlock();
        }
    }
}
