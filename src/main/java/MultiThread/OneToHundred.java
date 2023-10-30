package MultiThread;

public class OneToHundred {
    public static void main(String[] args) {

        PrintRunnable runnable1=new PrintRunnable(1);
        PrintRunnable runnable2=new PrintRunnable(2);
        PrintRunnable runnable3=new PrintRunnable(0);

        Thread t1=new Thread(runnable1,"T1");
        Thread t2=new Thread(runnable2,"T2");
        Thread t3=new Thread(runnable3,"T3");

        t1.start();
        t2.start();
        t3.start();
    }
}

