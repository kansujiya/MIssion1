package MultiThread.Youtube.MonitorLock;

public class MonitorLock {
    public static void main(String[] args) {

        MonitorLockExample obj1 = new MonitorLockExample();

        //way 1 : creating separate class of runnable
        MonitorThread1Runnable monitorThread1 = new MonitorThread1Runnable(obj1);
        Thread t1 = new Thread(monitorThread1);

        //way 2. As runnable is functional interface directly through lambda can invoke run method ()
        Thread t2 = new Thread( () -> obj1.task2());
        Thread t3 = new Thread( () -> obj1.task3());

        t1.start();
        t2.start();
        t3.start();
    }
}
