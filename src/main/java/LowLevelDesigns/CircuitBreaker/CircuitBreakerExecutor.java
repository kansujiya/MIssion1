package LowLevelDesigns.CircuitBreaker;

public class CircuitBreakerExecutor {
    private final CircuitBreaker circuitBreaker;
    private final RemoteService remoteService;


    public CircuitBreakerExecutor(CircuitBreaker circuitBreaker, RemoteService remoteService) {
        this.circuitBreaker = circuitBreaker;
        this.remoteService = remoteService;
    }

    public String execute() {
        if(!circuitBreaker.alloedRequest()) {
            return "Fallback: Service UnAvailable";
        }

        try {
            String result = remoteService.call();
            circuitBreaker.recordSuccess();
            return result;
        } catch (Exception e) {
            circuitBreaker.recordFailed();
            return "Fallback: Error occurred";
        }
    }
}
