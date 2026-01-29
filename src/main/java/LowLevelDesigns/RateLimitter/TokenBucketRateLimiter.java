package LowLevelDesigns.RateLimitter;

import java.util.HashMap;
import java.util.Map;

public class TokenBucketRateLimiter implements RateLimiter {

    private final RateLimitConfig config;

    private static class Bucket {
        double tokens;
        long lastRefillTime;

        Bucket(int capacity) {
            this.tokens = capacity;
            this.lastRefillTime = System.nanoTime();
        }
    }

    private final Map<String, Bucket> buckets = new HashMap<>();

    public TokenBucketRateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public synchronized boolean allow(String key) {
        Bucket b = buckets.computeIfAbsent(key, k -> new Bucket(config.capacity));
        refill(b);

        if (b.tokens >= 1) {
            b.tokens--;
            return true;
        }
        return false;
    }

    private void refill(Bucket b) {
        long now = System.nanoTime();
        double seconds = (now - b.lastRefillTime) / 1_000_000_000.0;
        double newTokens = seconds * config.refillRate;

        if (newTokens > 0) {
            b.tokens = Math.min(config.capacity, b.tokens + newTokens);
            b.lastRefillTime = now;
        }
    }

}
