package MultiThread.Atomic;

import java.util.concurrent.locks.Lock;

public class Subtract implements Runnable {
    Count count;
    Lock lock;

    public Subtract(Count c, Lock lock) {
        this.count = c;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (count) {
          for(int i = 0; i < 100; i++){
              lock.lock();


              if (i >= 50) {
                  System.out.println("STOP");
              }
              count.value--;
              System.out.println("Subtract");
              System.out.println(count.value);
              lock.unlock();
          }
        }
    }
}
