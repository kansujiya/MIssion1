package threads.CycleBarrier;

import java.util.concurrent.CyclicBarrier;

class BarrierTask implements Runnable {
    private CyclicBarrier barrier;
    public BarrierTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()
                    + " Completed first half, waiting others to complete");
            barrier.await();
            System.out.println(Thread.currentThread().getName()
                                       + "Started completing second half");
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}