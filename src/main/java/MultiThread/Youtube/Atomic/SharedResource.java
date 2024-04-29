package MultiThread.Youtube.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResource {

    int counter = 0;
    AtomicInteger atomicCounter = new AtomicInteger(0); //CAS

    public void increment() {
        counter++; // this is not thread safe, three step happening here | Read counter value + increment by 1 + reassign
        //1. use sync
        //2. using lock free operation like AtomicInteger
        atomicCounter.incrementAndGet();
    }

    public int get() {
        return counter;
    }

    public int getAtomic() {
        return atomicCounter.get();
    }
}
