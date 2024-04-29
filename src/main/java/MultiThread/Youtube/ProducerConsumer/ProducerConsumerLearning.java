package MultiThread.Youtube.ProducerConsumer;

public class ProducerConsumerLearning {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(3);

        Thread consumerThread = new Thread(()-> {
            try{
                for(int i =0; i <=6; i++) {
                    sharedResource.consume();
                }
            } catch (Exception e) {}

        });

        Thread producerThread = new Thread(() -> {
            try{
                for(int i =0; i <=6; i++) {
                    sharedResource.produce(i);
                }
            } catch (Exception e) {}
        });

        consumerThread.start();
        producerThread.start();
    }
}
