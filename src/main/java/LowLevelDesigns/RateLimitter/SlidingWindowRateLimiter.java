package LowLevelDesigns.RateLimitter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindowRateLimiter implements RateLimiter {
    private RateLimitConfig config;

    private final Map<String, Deque<Long>> logs = new HashMap<>();

    public SlidingWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public synchronized boolean allow(String key) {
        long now = System.currentTimeMillis();
        Deque<Long> q = logs.computeIfAbsent(key, k -> new ArrayDeque<>());

        while (!q.isEmpty() && now - q.peekFirst() > config.windowMillis) {
            q.pollFirst();
        }

        if (q.size() < config.limit) {
            q.addLast(now);
            return true;
        }
        return false;
    }
}
