package concurrency2.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

    private int notsafe;
    private AtomicInteger safe;

    public Atomic() {
        notsafe = 0;
        safe = new AtomicInteger();
        safe.set(0);
    }

    public static void main(String[] args) throws InterruptedException {
        final Atomic atomic = new Atomic();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomic.run();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomic.run();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.err.println(atomic.notsafe);
        System.err.println(atomic.safe);

    }

    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            notsafe++;
            safe.incrementAndGet();
        }
    }

}
