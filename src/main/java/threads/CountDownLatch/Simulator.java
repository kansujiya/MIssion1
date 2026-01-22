package threads.CountDownLatch;

public class Simulator {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(){
            @Override
            public void run(){
                try {
                    countDownLatch.decrementCount();
                    countDownLatch.waitForZero();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                try {
                    countDownLatch.decrementCount();
                    countDownLatch.waitForZero();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
