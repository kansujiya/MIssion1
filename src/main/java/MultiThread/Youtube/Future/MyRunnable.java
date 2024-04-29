package MultiThread.Youtube.Future;

import java.util.List;

public class MyRunnable implements Runnable {

    List<Integer> list;

    public MyRunnable(List<Integer> list) {
        this.list = list;
        list.add(300);
    }


    @Override
    public void run() {

    }
}
