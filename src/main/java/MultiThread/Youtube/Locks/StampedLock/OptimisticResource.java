package MultiThread.Youtube.Locks.StampedLock;

import java.util.concurrent.locks.StampedLock;

public class OptimisticResource {

    StampedLock stampedLock = new StampedLock();
    private int value = 10;

    public void write() {
        long stamp = stampedLock.tryOptimisticRead();
        try{
            System.out.println("Optimistic Read lock acquired by : " + Thread.currentThread().getName());
            Thread.sleep(5000);
            value = 11;
            if(stampedLock.validate(stamp)) {
                System.out.println("update value successfully");
            } else {
                System.out.println("rollback value");
                value = 10;
            }
        }catch (Exception e) {

        }
    }

    public void read() {
        long stamp = stampedLock.writeLock();
        System.out.println("Write lock acquired by : " + Thread.currentThread().getName());

        try{
            System.out.println("updating value");
            value = 9;
        } catch (Exception e) {

        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

}
