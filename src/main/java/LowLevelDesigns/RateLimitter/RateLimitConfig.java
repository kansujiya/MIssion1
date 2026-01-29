package LowLevelDesigns.RateLimitter;

public class RateLimitConfig {
    int capacity;        // for token bucket
    int refillRate;      // tokens/sec

    int limit;           // for window-based
    long windowMillis;

    public RateLimitConfig(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    public RateLimitConfig(int limit, long windowMillis) {
        this.limit = limit;
        this.windowMillis = windowMillis;
    }
}
