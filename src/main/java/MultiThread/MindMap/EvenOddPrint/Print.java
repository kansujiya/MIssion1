package MultiThread.MindMap.EvenOddPrint;

public class Print {

    public  int n = 0;
    public  int maxN = 10;

    public  synchronized void evenPrint() throws InterruptedException {

        while (n<=maxN) {
            if(n%2 != 0) {
                wait();
            }

            System.out.println(Thread.currentThread().getName() + " " + n);

            n++;
            notify();
        }
    }

    public synchronized  void oddPrint() throws InterruptedException {

        while (n<=maxN) {
            if(n%2 == 0) {
                wait();
            }

            System.out.println(Thread.currentThread().getName() + " " + n);

            n++;
            notify();
        }

    }
}
