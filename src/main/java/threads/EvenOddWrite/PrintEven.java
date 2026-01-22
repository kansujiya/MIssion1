package threads.EvenOddWrite;

import java.util.concurrent.TimeUnit;

public class PrintEven implements Runnable {
    private EvenOdd evenOdd =  null;
    public PrintEven(EvenOdd evenOdd) {
        this.evenOdd =  evenOdd;
    }

    @Override
    public void run() {
        while(true){
            try {
                evenOdd.printEven(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}