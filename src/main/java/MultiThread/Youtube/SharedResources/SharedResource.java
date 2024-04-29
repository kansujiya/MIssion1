package MultiThread.Youtube.SharedResources;

public class SharedResource {

    boolean isItemAvailable;

    public synchronized void addItem() {
        isItemAvailable = true;
        System.out.println("producer thread notifying all");
        notifyAll();
    }

    public synchronized void  consumerItem() {

        System.out.println("Consumer thread inside consumer Item method");

        while(!isItemAvailable) {
            try {
                System.out.println("Consumer thread is waiting");
                wait();
            } catch (Exception e) {}
        }
        System.out.println("Consumer thread consumed item");
        isItemAvailable = false;
    }

}
