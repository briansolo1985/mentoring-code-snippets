package concurrency2.lock;

import java.util.concurrent.ThreadLocalRandom;

class Producer implements Runnable {

    private final ThreadSafeCollection pc;

    public Producer(ThreadSafeCollection sharedObject) {
        pc = sharedObject;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pc.put(ThreadLocalRandom.current().nextInt(0, 1000));
                Thread.sleep((int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}