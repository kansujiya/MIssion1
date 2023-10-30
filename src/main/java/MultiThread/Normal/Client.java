package MultiThread.Normal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {
        Count c = new Count(0);

        Adder add = new Adder(c);
        Subtract sub = new Subtract(c);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(add);
        executor.submit(sub);
    }
}
