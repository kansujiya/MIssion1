package threads.CycleBarrier;

import java.util.concurrent.CyclicBarrier;

public class Simulator {
    public static void main(String[] args) {
        final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("All parties finished the first half");
            }
        });

//        new Thread(()->{
//                while (true){
//                    System.out.println("hi");
//                }
//        }).start();

        new Thread(new BarrierTask(cb), "Thread 1").start();
        new Thread(new BarrierTask(cb), "Thread 2").start();
        new Thread(new BarrierTask(cb), "Thread 3").start();
    }
}






