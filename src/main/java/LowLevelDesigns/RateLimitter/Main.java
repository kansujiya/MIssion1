package LowLevelDesigns.RateLimitter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 5 requests per second
        RateLimiterService limiter =
                new RateLimiterService(new RateLimitConfig(5, 5));

        String user = "user-1";

        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + i + " allowed: " +
                    limiter.isAllowed(user));
            Thread.sleep(100);
        }
    }
}
