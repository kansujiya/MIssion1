package MultiThread.Youtube.Locks.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {

    boolean isActive = false;

    public void read(ReadWriteLock lock) {
        try{
            lock.readLock().lock();
            System.out.println("Read lock acquired by :" + Thread.currentThread().getName());
            System.out.println("Read value of isActive is :" + isActive);
            Thread.sleep(8000);
        } catch (Exception e) {

        } finally {
            System.out.println("Read lock released by :" + Thread.currentThread().getName());
            lock.readLock().unlock();
        }
    }

    public void write(ReadWriteLock lock) {
        try{
            lock.writeLock().lock();
            System.out.println("Write lock acquired by :" + Thread.currentThread().getName());
            isActive = !isActive;
        } catch (Exception e) {

        } finally {
            lock.writeLock().unlock();
            System.out.println("Write lock released by :" + Thread.currentThread().getName());
        }
    }

}
