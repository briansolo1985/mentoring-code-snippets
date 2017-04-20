package concurrency.server;

import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {
    private ThreadSafeContainer container;
    private int number;

    public Producer(ThreadSafeContainer c, int number) {
        container = c;
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            int i = ThreadLocalRandom.current().nextInt(0, 1000);
            container.put(i);
            System.out.println("Producer #" + this.number + " put: " + i);
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
            }
        }
    }
}
