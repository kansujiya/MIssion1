package LowLevelDesigns.LRUCache;

public class Main {
    public static void main(String[] args) {

        LRUCache cache = new LRUCache(3);

        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);

        System.out.println(cache.get(1)); // 10 (1 becomes most recently used)

        cache.put(4, 40); // Evicts key 2 (least recently used)

        System.out.println(cache.get(2)); // -1 (not found, evicted)
        System.out.println(cache.get(3)); // 30
        System.out.println(cache.get(4)); // 40

        cache.put(5, 50); // Evicts key 1

        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(5)); // 50
    }
}
