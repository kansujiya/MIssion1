package LowLevelDesigns.RateLimitter;

public class RateLimitConfig {
    final int capacity;        // max tokens
    final int refillRate;      // tokens per second

    public RateLimitConfig(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }
}
