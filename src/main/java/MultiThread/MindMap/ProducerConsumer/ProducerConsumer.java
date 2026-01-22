package MultiThread.MindMap.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;


//Actor - Producer, Consumer
//Resource - Queue
//Rule - Coordination + Mutual Elusion, If queue empty Or Queue is Full

public class ProducerConsumer {

    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 5;

    // Actor-1 : Producer
    public synchronized void produce(int value) throws InterruptedException {

        // Coordination rule: wait if buffer full
        while (buffer.size() == capacity) {
            wait();
        }

        buffer.add(value);
        System.out.println("Produced: " + value);

        notify(); // wake consumer
    }

    // Actor-2 : Consumer
    public synchronized void consume() throws InterruptedException {

        // Coordination rule: wait if buffer empty
        while (buffer.isEmpty()) {
            wait();
        }

        int value = buffer.poll();
        System.out.println("Consumed: " + value);

        notify(); // wake producer
    }

    public static void main(String[] args) {

        ProducerConsumer pc = new ProducerConsumer();

        // Producer thread
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    pc.produce(i);
                    Thread.sleep(500);
                }
            } catch (Exception e) {}
        }).start();

        // Consumer thread
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    pc.consume();
                    Thread.sleep(1000);
                }
            } catch (Exception e) {}
        }).start();
    }
}

