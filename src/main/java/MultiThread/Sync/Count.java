package MultiThread.Sync;

public class Count {
    int value;

    public Count(int i) {
        this.value = i;
    }

    synchronized void add(int val) {
        // synchronized(this)
        value += val;
        // }
    }

    synchronized void subtract(int val) {
        // synchronized(this)
        value -= val;
        // }
    }
}
