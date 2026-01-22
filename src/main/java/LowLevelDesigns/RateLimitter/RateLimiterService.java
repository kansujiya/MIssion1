package LowLevelDesigns.RateLimitter;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {

    private final RateLimitConfig config;
    //One TokenBucket per client (userId / IP / API key)
    private final ConcurrentHashMap<String, TokenBucket> buckets = new ConcurrentHashMap<>();

    public RateLimiterService(RateLimitConfig config) {
        this.config = config;
    }

    public boolean isAllowed(String clientId) {
        TokenBucket bucket = buckets.computeIfAbsent(
                clientId,
                k -> new TokenBucket(config.capacity, config.refillRate)
        );

        return bucket.allowRequest();
    }
}
