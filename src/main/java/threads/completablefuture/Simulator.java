package threads.completablefuture;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;

public class Simulator {
    public static void main(String[] args) {

        //Predicate - Filter
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer value) {
                return value % 2 == 0;
            }
        };

//        CompletableFuture.supplyAsync(()->Arrays.asList(1, 2, 3))
//                .thenApplyAsync(list->{
//                    List<Integer> l = new ArrayList<>();
//
//                    list.forEach(x-> {
//                        if (predicate.test(x)) {
//                            l.add(x);
//                        }
//                    });
//
//                    return l;
//                }).thenAcceptAsync(list-> {
//                    list.forEach(x-> System.out.println(x));
//                }).exceptionally(e->{
//                    e.getMessage();
//                    return null;
//                }).join();

        //Supplier - Source
        CompletableFuture<List<Integer>> f1 =
                CompletableFuture.supplyAsync(() -> Arrays.asList(1, 2, 3));


        //Function - Operation/Transformation
        CompletableFuture<List<Integer>> f2 = f1.thenApplyAsync(list -> {
            List<Integer> l = new ArrayList<>();
            list.forEach(x -> {
                if (predicate.test(x)) {
                    l.add(x);
                }
            });
            return l;
        });

        //Consumer - Sink
        CompletableFuture<Void> f3 = f2.thenAcceptAsync(list -> {
            list.forEach(x -> System.out.println(x));
        });

        f3. join();

        int[] arr = new int[]{2, 4, 6};
        List<int []> list = Arrays.asList(new int[]{2, 4}, new int[]{2, 4});
        Collections.sort(list, (a,b) -> Integer.compare(a[0], b[0]));
        Function<Integer, String> toString = Object::toString;
        Function<String, String>     quote = s -> "'" + s + "'";

        Function<Integer, String> str = quote.compose(Object::toString);

        System.out.println(str.apply(5));
    }


}
