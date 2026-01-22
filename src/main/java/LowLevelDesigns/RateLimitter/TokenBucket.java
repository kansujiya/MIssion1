package LowLevelDesigns.RateLimitter;

public class TokenBucket {
    private final int capacity;
    private final int refillRate;

    private double tokens;
    private long lastRefillTime;

    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;
        this.lastRefillTime = System.nanoTime();
    }

    // synchronized for thread-safety
    public synchronized boolean allowRequest() {
        refill();

        if (tokens >= 1) {
            tokens -= 1;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double seconds = (now - lastRefillTime) / 1_000_000_000.0;

        double newTokens = seconds * refillRate;
        if (newTokens > 0) {
            tokens = Math.min(capacity, tokens + newTokens);
            lastRefillTime = now;
        }
    }
}
