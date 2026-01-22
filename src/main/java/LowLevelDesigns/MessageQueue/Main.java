package LowLevelDesigns.MessageQueue;

//Publisher → Broker → Topic → Consumer
//Publisher → produces messages
//
//Broker → routes & stores messages
//
//Topic → holds message stream
//
//Consumer → reads messages

public class Main {
    public static void main(String[] args) {
        Broker broker = new Broker();

        broker.createTopic("orders");

        Subscriber c1 = new Subscriber("C1");
        Subscriber c2 = new Subscriber("C2");

        broker.subscribe("orders", c1);
        broker.subscribe("orders", c2);

        Publisher p1 = new Publisher("P1", broker);
        Publisher p2 = new Publisher("P2", broker);

        p1.publish("orders", "order-1");
        p2.publish("orders", "order-2");

        broker.poll("orders");

        p1.publish("orders", "order-3");

        broker.poll("orders");
    }
}
