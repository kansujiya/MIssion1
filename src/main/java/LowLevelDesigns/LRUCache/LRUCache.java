package LowLevelDesigns.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> map;

    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null) return -1;

        remove(node);
        addToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;

            remove(node);
            addToFront(node);
            return;
        }

        if(map.size() == capacity) {
            // Remove LRU node (before tail)
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }

        Node newNode = new Node(key, value);
        map.put(key, newNode);
        addToFront(newNode);
    }

    public void remove(Node node) {
        node.prev.next = node.next; // 1 2 3 => 2 delete => connect 1 and 3
        node.next.prev = node.prev;
    }

    public void addToFront(Node node) {
        node.next = head.next; //1 2 3 => add 4 here at front => so assign 4 connections between head new node and node
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

}
