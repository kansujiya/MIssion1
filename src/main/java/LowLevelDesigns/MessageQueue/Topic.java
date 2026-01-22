package LowLevelDesigns.MessageQueue;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    final String name;
    final List<Message> list = new ArrayList<>();

    public Topic(String name) {
        this.name = name;
    }

    public void publish(Message message) {
        list.add(message);
    }

    public synchronized Message get(int offset) {
        if(offset >= list.size()) return null;
        return list.get(offset);
    }

    public synchronized int getSize() {
        return list.size();
    }
}
