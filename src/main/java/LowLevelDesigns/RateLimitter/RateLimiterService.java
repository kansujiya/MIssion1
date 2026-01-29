package LowLevelDesigns.RateLimitter;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {

    private final RateLimiter limiter;

    public RateLimiterService(RateLimiter limiter) {
        this.limiter = limiter;
    }

    public boolean isAllowed(String clientId) {
        return limiter.allow(clientId);
    }
}
