package MultiThread.HunderedExecutor;

public class NumberPrinter implements Runnable {
    int numberToPrint;

    public NumberPrinter(int number) {
        this.numberToPrint = number;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (numberToPrint == 60) {
            System.out.println("Break");
        }
        System.out.println("Number: " + numberToPrint +
                " Printed By: " + Thread.currentThread().getName());
    }
}
