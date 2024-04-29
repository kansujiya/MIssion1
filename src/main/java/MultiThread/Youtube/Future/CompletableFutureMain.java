package MultiThread.Youtube.Future;

//In Java 8
//Async programming
//Advance version of Future
//Provide additional capability like chaining

//public static<T> CompletableFuture<T> supplyAsync(Supplier<T> supplier, Executor executor) //default will use fork join
//public static<T> CompletableFuture<T> supplyAsync(Supplier<T> supplier)


import java.util.concurrent.*;

public class CompletableFutureMain {
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,1,
                TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread name of supply async "+ Thread.currentThread().getName());
            try{
                Thread.sleep(5000);
            } catch (Exception e) {}
            return "Task 1 of supply async";
        }, executor);

        CompletableFuture<String> completableFuture2 = completableFuture1.thenApply((String fromSupplyAsync)-> {
            System.out.println("Thread name of then apply "+ Thread.currentThread().getName());
            return fromSupplyAsync + " And also Task 2 from then apply";
        });

        //if we use then apply async it will use different thread
        CompletableFuture<String> completableFuture3 = completableFuture1.thenApplyAsync((String fromSupplyAsync)-> {
            System.out.println("Thread name of then apply async"+ Thread.currentThread().getName());
            return fromSupplyAsync + " And also Task 3 from then apply async";
        });

        // then compose help in manage ordering of async tasks & async will use different thread
        CompletableFuture<String> completableFuture4 = completableFuture1.thenCompose((String fromSupplyAsync)-> {
            System.out.println("Thread name of then apply async"+ Thread.currentThread().getName());
            return CompletableFuture.supplyAsync(()-> {
                return fromSupplyAsync + " And also Task 4 from then compose";
            });
        }).thenComposeAsync((String fromCompose) -> {
            return CompletableFuture.supplyAsync(() -> {
                    return fromCompose + "Task 5";
            });
        }, executor);

        //Accept is the end of chai stage
        completableFuture4.thenAccept((String value) -> {
            System.out.println("Final value of accept or end of chain " + value);
        });


        //Combine : used to combine two completable future
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
           return 10;
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            return "k";
        });

        CompletableFuture<String> combine = task1.thenCombine(task2, (Integer v1, String v2) ->
            v1 + v2 );


        try {
            System.out.println(completableFuture1.get());
            System.out.println(completableFuture2.get());
            System.out.println(completableFuture3.get());
            System.out.println(completableFuture4.get());

            System.out.println(combine.get());
        } catch (Exception e) {}
    }
}
