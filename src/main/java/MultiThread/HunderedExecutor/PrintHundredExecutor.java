package MultiThread.HunderedExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintHundredExecutor {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(10);


        for(int i = 0; i < 100 ; i++) {

            Thread.sleep(50);
            if (i == 60) {
                System.out.println("STOP");
            }
            NumberPrinter np = new NumberPrinter(i);
            executors.execute(np);
        }

//        for (int i = 1; i <= 100; i++) {
//
//            if (i == 60) {
//                System.out.println("Break");
//            }
//            NumberPrinter np = new NumberPrinter(i);
//            Thread t = new Thread(np);
//            t.start();
//        }

        executors.shutdown();
    }
}
