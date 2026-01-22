package MultiThread.FiveProblem;

public class AlternateWaitNotify {
    public static void main(String[] args) {
        Object lock = new Object();
        char[] letters = "ABCDEFGHIJ".toCharArray();
        int max = letters.length;

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= max; i++) {
                synchronized (lock) {
                    System.out.print(i);
                    lock.notifyAll();
                    try { if (i < max) lock.wait(); } catch (InterruptedException e){ Thread.currentThread().interrupt(); }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (char c : letters) {
                synchronized (lock) {
                    try { lock.wait(); } catch (InterruptedException e){ Thread.currentThread().interrupt(); }
                    System.out.print(c);
                    lock.notifyAll();
                }
            }
        });

        t2.start();
        t1.start();
    }
}
