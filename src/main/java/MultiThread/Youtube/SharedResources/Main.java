package MultiThread.Youtube.SharedResources;

public class Main {

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();

        Thread producer = new Thread( () -> {
            try{
                Thread.sleep(2000);
            } catch (Exception e) {}

            sharedResource.addItem();
        });

        Thread consumer = new Thread(()-> {
           sharedResource.consumerItem();
        });

        producer.start();
        consumer.start();
    }

}
