package LowLevelDesigns.RateLimitter;

import java.util.HashMap;
import java.util.Map;

public class FixedWindowRateLimiter implements RateLimiter {
    private final RateLimitConfig config;

    private static class Window {
        long startTime;
        int count;
    }

    private final Map<String, Window> windows = new HashMap<>();

    public FixedWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public synchronized boolean allow(String key) {
        long now = System.currentTimeMillis();
        Window w = windows.computeIfAbsent(key, k -> {
            Window nw = new Window();
            nw.startTime = now;
            return nw;
        });

        if (now - w.startTime >= config.windowMillis) {
            w.startTime = now;
            w.count = 0;
        }

        if (w.count < config.limit) {
            w.count++;
            return true;
        }
        return false;
    }
}
