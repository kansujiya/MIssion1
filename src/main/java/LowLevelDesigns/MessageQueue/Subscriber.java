package LowLevelDesigns.MessageQueue;

import java.util.HashMap;
import java.util.Map;

public class Subscriber {
    final String id;
    private final Map<String , Integer> offsets = new HashMap<>();


    public Subscriber(String id) {
        this.id = id;
    }

    public void subscribe(Topic topic) {
        int offset = offsets.getOrDefault(topic.name, 0);

        Message msg;
        while ((msg = topic.get(offset)) != null) {  //in while condition keep reading message
            System.out.println("Consumer " + id +
                    " read from " + topic.name + ": " + msg.data);
            offset++;
        }

        offsets.put(topic.name, offset);
    }
}
