package LowLevelDesigns.ThreadPoolExecutor;

//Requirements
//
//Submit tasks (Runnable)
//
//Fixed number of worker threads
//
//Tasks go into a queue
//
//Workers continuously pick tasks and execute
//
//Graceful shutdown

public class Main {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(3, 5);

        for(int i = 0; i <= 10; i++) {
            int taskId = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(
                            Thread.currentThread().getName() +
                                    " executing task " + taskId);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {}
                }
            });
        }
    }
}
