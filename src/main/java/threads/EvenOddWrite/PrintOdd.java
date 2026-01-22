package threads.EvenOddWrite;

import java.util.concurrent.TimeUnit;

public class PrintOdd implements Runnable {
    private EvenOdd evenOdd =  null;
    public PrintOdd(EvenOdd evenOdd) {
        this.evenOdd =  evenOdd;
    }

    @Override
    public void run() {
        while(true){
            try {
                evenOdd.printOdd(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
