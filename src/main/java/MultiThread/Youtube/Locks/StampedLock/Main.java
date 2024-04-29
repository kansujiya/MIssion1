package MultiThread.Youtube.Locks.StampedLock;

public class Main {
    public static void main(String[] args) {

        //1. Similar to read write
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(()-> {
            sharedResource.read();
        });

        Thread t2 = new Thread(()-> {
            sharedResource.read();
        });

        Thread t3 = new Thread(()-> {
            sharedResource.write();
        });

//        t1.start();
//        t2.start();
//        t3.start();

        //2. Optimistic lock
        OptimisticResource optimisticResource = new OptimisticResource();

        Thread t4 = new Thread(()-> {
           optimisticResource.read();
        });

        Thread t5 = new Thread(()-> {
           optimisticResource.write();
        });

        t4.start();
        t5.start();
    }
}
