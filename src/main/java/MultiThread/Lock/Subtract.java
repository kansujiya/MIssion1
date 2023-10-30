package MultiThread.Lock;

public class Subtract implements Runnable {
    Count count;

    public Subtract(Count c) {
        this.count = c;
    }

    @Override
    public void run() {
        synchronized (count) {
          for(int i = 0; i < 100; i++){
              this.count.value -= i;
              System.out.println(count.value);
          }
        }
    }
}
