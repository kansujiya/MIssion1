package MultiThread.MindMap.EvenOddPrint;


//Actor - EvenPrint & Odd Print
//Resource - Mutable + Shared + Write Access
// Rule - Coordination + Ordering + Resource Control


//
//ACTORS     → printOdd(), printEven()
//RESOURCE   → number
//RULES
//   - Coordination → while + wait()
//   - Resource control → synchronized
//   - Ordering → number++
//TOOL       → synchronized + wait/notify

public class  EvenOddPrint {

    int MaxN = 10;

    int n = 1;

//    public EvenOddPrint(int n) {
//        this.n = n;
//    }

    public synchronized void printEven() {
        while(n <= MaxN) {
            try {
                if (n % 2 != 0) {
                    wait();
                }

                System.out.println(Thread.currentThread().getName() + " number is " + n);
                n++;
                notifyAll();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void printOdd() {
        while(n <= MaxN) {
            try {
                if (n % 2 == 0) {
                    wait();
                }

                System.out.println( Thread.currentThread().getName() + " Number is " + n);
                n++;
                notifyAll();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
