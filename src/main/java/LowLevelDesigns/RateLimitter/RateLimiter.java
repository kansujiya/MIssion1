package LowLevelDesigns.RateLimitter;

public interface RateLimiter {
    boolean allow(String key);
}
