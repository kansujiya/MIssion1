package LowLevelDesigns.CircuitBreaker;

import java.rmi.Remote;

public class Main {

    public static void main(String[] args) {
        CircuitBreaker breaker = new CircuitBreaker(3, 3000);

        RemoteService service = () -> {
            if(Math.random() > 0.7) {
                throw new RuntimeException("Service Failed");
            }
            return "Success";
        };

        CircuitBreakerExecutor circuitBreakerExecutor = new CircuitBreakerExecutor(breaker, service);

        for(int i = 0; i < 10; i++) {
            System.out.println("State=" + breaker.getState() +
                    ", Response=" + circuitBreakerExecutor.execute());
        }

        try { Thread.sleep(500); } catch (Exception ignored) {}

    }

}
