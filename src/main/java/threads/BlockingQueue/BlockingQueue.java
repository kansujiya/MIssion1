package threads.BlockingQueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<I extends Number> {
    private LinkedList<Integer> linkedList = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition full  = lock.newCondition();
    private Condition empty = lock.newCondition();
    private int capacity;
    public  BlockingQueue(int capacity){
        this.capacity = capacity;
    };

    public void produce(Integer value) throws InterruptedException {
        try{
            lock.lock();
            while(linkedList.size() >= capacity){
                full.await();
            }
            linkedList.add(value);
            empty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public Integer consume() throws InterruptedException {
        try{
            lock.lock();
            while(linkedList.size() <= 0){
                empty.await();
            }
            Integer value = linkedList.poll();
            full.signalAll();
            return value;
        }finally {
            lock.unlock();
        }
    }
}
