package MultiThread.Youtube.Locks.Semaphore;

import javax.sound.sampled.FloatControl;
import java.util.concurrent.Semaphore;

public class SharedResource {

    int value = 10;
    Semaphore semaphore = new Semaphore(2);

    public void read() {
        try {
            semaphore.acquire();
            System.out.println("Semaphore lock acquired by :" + Thread.currentThread().getName());
            Thread.sleep(5000);
        } catch (Exception e) {

        } finally {
            semaphore.release();
            System.out.println("Semaphore lock released by :" + Thread.currentThread().getName());
        }

    }
}
