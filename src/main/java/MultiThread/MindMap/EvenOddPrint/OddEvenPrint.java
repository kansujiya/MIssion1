package MultiThread.MindMap.EvenOddPrint;


// 1...10 Print by 2 thread even Odd

// Actor - EventPrinter, Odd Printer
// Resource - int value
// Rule -> Tool -> Coordination, Resource Control

public class OddEvenPrint {



    public static void main(String[] args) {

        Print p = new Print();

        Thread t1 = new Thread(()-> {

            try {
                p.evenPrint();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.setName("t1");

        Thread t2 = new Thread(()-> {

            try {
                p.oddPrint();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t2.setName("t2");

        t1.start();
        t2.start();

    }





}
