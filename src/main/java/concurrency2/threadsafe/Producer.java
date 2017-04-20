package concurrency2.threadsafe;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
    private BlockingQueue<Integer> container;
    private int number;

    public Producer(BlockingQueue<Integer> c, int number) {
        this.container = c;
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }

    private void produce() {
        try {
            int i = ThreadLocalRandom.current().nextInt(0, 1000);
            container.put(i);
            System.out.println("Producer #" + this.number + " put: " + i);
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e1) {
        }
    }
}
