package LowLevelDesigns.MessageQueue;

public class Publisher {

    private final String id;
    private final Broker broker;


    public Publisher(String id, Broker broker) {
        this.id = id;
        this.broker = broker;
    }

    public void publish(String topicName, String data) {
        System.out.println("Publisher " + id + " publishing to " +
                topicName + ": " + data);
        broker.publish(topicName, data);
    }
}
