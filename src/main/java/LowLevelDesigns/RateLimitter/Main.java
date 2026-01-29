package LowLevelDesigns.RateLimitter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 5 requests per second

        RateLimitConfig config1 = new RateLimitConfig(5,5);
        RateLimitConfig config2 = new RateLimitConfig(5, 1000);

        RateLimiterService limiter1 =
                new RateLimiterService(new TokenBucketRateLimiter(config1));

        RateLimiterService limiter2 =
                new RateLimiterService(new SlidingWindowRateLimiter(config1));

        // or new FixedWindowRateLimiter(5, 1000);
        // or new SlidingWindowRateLimiter(5, 1000);

        String user = "user-1";

        for (int i = 0; i < 100; i++) {
            System.out.println("Request " + i + " allowed: " +
                    limiter1.isAllowed(user));

            System.out.println("Request " + i + " allowed: " +
                    limiter2.isAllowed(user));

            Thread.sleep(100);
        }
    }
}
