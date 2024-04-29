package MultiThread.Youtube.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private Queue<Integer> sharedBuffer;
    private int bufferSize;

    public SharedResource(int bufferSize) {
        sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized int consume() {
        //Buffer is empty, Consumer is waiting for producer to produce item
        while(sharedBuffer.isEmpty()) {
            System.out.println("Buffer is empty, Consumer is waiting for producer to produce item");
            try{
                wait();
            } catch (Exception e) {}
        }

        int item = sharedBuffer.poll();
        System.out.println("consumed: " + item);
        //notify the producer that there is space in buffer
        notify();
        return item;
    }

    public synchronized void produce(int item) {
        // if buffer is full then wait for consumer to consumer
        while(sharedBuffer.size() == bufferSize) {
            System.out.println("Buffer is full, Producer waiting to Consumer to consume");
            try{
                wait();
            } catch (Exception e) {}
        }

        sharedBuffer.add(item);
        System.out.println("produced: " + item);
        //notify consumer to consumer, item is available
        notify();

    }
}
