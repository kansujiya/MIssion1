package LinkedList;

import java.util.HashMap;
import java.util.Map;

class CacheNode {
    int key;
    int value;
    CacheNode prev;
    CacheNode next;
}

//Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
//
//Implement the LRUCache class:
//
//LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
//int get(int key) Return the value of the key if the key exists, otherwise return -1.
//void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
//The functions get and put must each run in O(1) average time complexity.

//Input
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, null, -1, 3, 4]

class LRUCacheCaller {
    public static void main(String args[]) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1); //cache is {1=1}
        cache.put(2,2); //cache is {1=1, 2=2}
        System.out.println(cache.get(1)); //return 1
        cache.put(3,3); //LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(cache.get(2)); //returns -1 (not found)
        cache.put(4,4); //LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(cache.get(1)); //return -1 (not found)
        System.out.println(cache.get(3)); //return 3
        System.out.println(cache.get(4)); //return 4
    }
}

public class LRUCache {

    private Map<Integer, CacheNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private CacheNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new CacheNode();
        tail = new CacheNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        CacheNode node = cache.get(key);
        if(node == null) return -1;

        //move to accessed node to head
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        CacheNode node = cache.get(key);

        if(node == null) {
            CacheNode newNode = new CacheNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            size++;

            addNode(newNode);

            //more than capacity remove tail
            if(size > capacity) {
                CacheNode tail = popTail();
                cache.remove(tail.key);
                size--;
            }

        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    public void moveToHead(CacheNode node) {
        removeNode(node);
        addNode(node);
    }

    public void removeNode(CacheNode node) {
        //remove last node
        CacheNode prev = node.prev;
        CacheNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    public CacheNode popTail() {
        CacheNode node = tail.prev;
        removeNode(node);
        return node;
    }

    public void addNode(CacheNode node) { //keep in mind that we have HEAD & NEXT only so do link based on these 2 nodes only
        //Always add new node after head
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }
}
