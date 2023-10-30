package MultiThread.MergeSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MergeSort {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(2);
        list.add(8);
        list.add(11);
        list.add(4);
        list.add(3);
        list.add(9);
        list.add(1);


        ExecutorService executorService = Executors.newCachedThreadPool();
        Sorter sort = new Sorter(list, executorService);

        Future<List<Integer>> resultFuture = executorService.submit(sort);

        List<Integer> resultArray = resultFuture.get();

        for (Integer in: resultArray) {
            System.out.println(in);
        }

        executorService.shutdown();


    }
}
