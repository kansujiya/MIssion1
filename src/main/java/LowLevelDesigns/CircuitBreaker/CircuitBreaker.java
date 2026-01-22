package LowLevelDesigns.CircuitBreaker;

public class CircuitBreaker {

    private final int failurrThreshold;
    private final long resetTimeoutMillis;

    private int failureCount = 0;
    private long lastFailureTime = 0;
    private CircuitState state = CircuitState.CLOSED;


    public CircuitBreaker(int failurrThreshold, long resetTimeoutMillis) {
        this.failurrThreshold = failurrThreshold;
        this.resetTimeoutMillis = resetTimeoutMillis;
    }

    public synchronized boolean alloedRequest() {
        if(state == CircuitState.OPEN) {
            if(System.currentTimeMillis() - lastFailureTime > resetTimeoutMillis) {
                state = CircuitState.HALF_OPEN;
                return true;
            }
            return false;
        }
        return true;
    }

    public synchronized void recordSuccess() {
        failureCount++;
        state = CircuitState.CLOSED;
    }

    public synchronized void recordFailed() {
        failureCount++;
        lastFailureTime = System.currentTimeMillis();

        if(failureCount >= failurrThreshold) {
            state = CircuitState.OPEN;
        }
    }

    public CircuitState getState() {
        return state;
    }
}
