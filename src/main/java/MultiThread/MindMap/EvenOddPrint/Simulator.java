package MultiThread.MindMap.EvenOddPrint;

// 1...10 Print by 2 thread even Odd

// Actor - EventPrinter, Odd Printer
// Resource
// Rule -> Tool

public class Simulator {

    public static void main(String[] args) {

        EvenOddPrint print = new EvenOddPrint();

        Thread t1 = new Thread(()-> {
            try{
                print.printEven();
            } catch (Exception e) {

            }
        });

        t1.setName("t1");



        Thread t2 = new Thread(() -> {
            try{
                print.printOdd();
            } catch (Exception e) {}
        });

        t2.setName("t2");

        t1.start();
        t2.start();

    }
}
