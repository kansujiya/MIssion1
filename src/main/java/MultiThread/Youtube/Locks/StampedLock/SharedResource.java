package MultiThread.Youtube.Locks.StampedLock;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    private int value = 9;

    StampedLock lock = new StampedLock();

    public void read() {
        long stamp = lock.readLock();
        try {
            System.out.println("Read lock acquired by :" + Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (Exception e) {}
        finally {
            lock.unlockRead(stamp);
            System.out.println("Read lock released by :" + Thread.currentThread().getName());
        }
    }

    public void write() {
        long stamp = lock.writeLock();
        try {
            System.out.println("Write lock acquired by :" + Thread.currentThread().getName());
            value = 10;
        } catch (Exception e) {}
        finally {
            lock.unlockRead(stamp);
            System.out.println("Write lock released by :" + Thread.currentThread().getName());
        }
    }

}
