package threads.EvenOddWrite;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EvenOdd {
    private int number = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition even = lock.newCondition();
    private Condition odd = lock.newCondition();

    public void printOdd(String name) throws InterruptedException{
        try{
            lock.lock();
            while(number %2 == 0){
                even.await();
            }
            System.out.println(name + ":" + number);
            number ++;
            odd.signal();

        }finally{
            lock.unlock();
        }
    }

    public void printEven(String name) throws InterruptedException{
        try{
            lock.lock();
            while(number % 2 != 0){
                odd.await();

            }
            System.out.println(name + ":" + number);
            number++;
            even.signal();
        }finally{
            lock.unlock();
        }
    }
}

