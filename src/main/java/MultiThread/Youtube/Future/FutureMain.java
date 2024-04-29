package MultiThread.Youtube.Future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//Future is interface
//Future Task is class of interface
public class FutureMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,1,
                TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        //submit(Runnable r)
        //submit(Runnable r, t)
        //submit(Callable<> c)

        //use case 1
        Future<?> future = executor.submit(() -> {
            System.out.println("This is runnable task, that executor will execute"); //Runnable Task
        });
        //Caller to check whether is done or not
        System.out.println(future.isDone());
        System.out.println(future.get());
        System.out.println(future.isDone());

        //use case 2
        List<Integer> list = new ArrayList<>();
        Future<List<Integer>> listFuture = executor.submit(new MyRunnable(list), list);
        try{
            //Way 1
            listFuture.get();
            System.out.println(list.get(0));

            //way 2
            List<Integer> output = listFuture.get();
            System.out.println(output.get(0));
        } catch (Exception e) {}


        //use case 3
        Future<Integer> intFuture = executor.submit(()-> {
            System.out.println("This is callable task, that executor will execute");
            return 10;
        });
        //Caller to check whether is done or not
        System.out.println(intFuture.isDone());
        System.out.println(intFuture.get());
        System.out.println(intFuture.isDone());

    }
}
