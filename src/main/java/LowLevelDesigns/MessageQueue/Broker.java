package LowLevelDesigns.MessageQueue;

import MultiThread.MindMap.ProducerConsumer.ProducerConsumerBQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Broker {
    private final Map<String, Topic> topics = new HashMap<>();
    private final Map<String, List<Subscriber>> subscribers = new HashMap<>();

    public Topic createTopic(String name) {
        Topic t = new Topic(name);
        topics.put(name, t);
        subscribers.put(name, new ArrayList<>());
        return t;
    }

    public void publish(String topicName, String Data) {
        Topic topic = topics.get(topicName);
        topic.publish(new Message(Data));
    }

    public void subscribe(String topicName, Subscriber s) {
        subscribers.get(topicName).add(s);
    }

    public void poll(String topicName) {
        Topic t = topics.get(topicName);
        for(Subscriber s: subscribers.get(topicName)) {
            s.subscribe(t);
        }
    }
}
