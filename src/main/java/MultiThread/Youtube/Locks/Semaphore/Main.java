package MultiThread.Youtube.Locks.Semaphore;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(() -> {
            sharedResource.read();
        });
        Thread t2 = new Thread(() -> {
            sharedResource.read();
        });
        Thread t3 = new Thread(() -> {
            sharedResource.read();
        });Thread t4 = new Thread(() -> {
            sharedResource.read();
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
