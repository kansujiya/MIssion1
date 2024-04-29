package LowLevelDesigns;

import java.util.concurrent.*;

public class ServiceExecutor {
    private final ExecutorService executor;

    public ServiceExecutor(int maxThreads) {
        this.executor = Executors.newFixedThreadPool(maxThreads);
    }

    public Future<?> executeService(Runnable serviceTask) {
        // Submit a service task for execution and return a Future representing that task
        return executor.submit(serviceTask);
    }

    public <T> Future<T> executeCallable(Callable<T> serviceTask) {
        // Submit a callable task for execution and return a Future representing that task
        return executor.submit(serviceTask);
    }

    public void shutdown() {
        // Gracefully shut down the executor service
        executor.shutdown();
    }

    public static void main(String[] args) {
        // Example usage
        ServiceExecutor serviceExecutor = new ServiceExecutor(5);

        // Submitting a Runnable task
        serviceExecutor.executeService(() -> System.out.println("Executing a service task"));

        // Submitting a Callable task
        Future<Integer> futureResult = serviceExecutor.executeCallable(() -> {
            // Simulating a time-consuming task
            Thread.sleep(2000);
            return 42;
        });

        // Retrieving the result of the Callable task
        try {
            int result = futureResult.get();
            System.out.println("Result of the Callable task: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Shutting down the executor service
        serviceExecutor.shutdown();
    }
}
