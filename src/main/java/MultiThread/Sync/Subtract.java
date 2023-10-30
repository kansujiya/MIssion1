package MultiThread.Sync;

public class Subtract implements Runnable {
    Count count;

    public Subtract(Count c) {
        this.count = c;
    }

    @Override
    public void run() {
        synchronized (count) {
          for(int i = 0; i < 100; i++){
              count.value--;
              System.out.println(count.value);
          }
        }
    }
}
