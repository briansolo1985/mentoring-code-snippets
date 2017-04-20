package concurrency2.lock;

public class Consumer implements Runnable {

    private final ThreadSafeCollection pc;

    public Consumer(ThreadSafeCollection sharedObject) {
        pc = sharedObject;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pc.get();
                Thread.sleep((int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
