package concurrency2.threadsafe;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Integer> container;
    private int number;

    public Consumer(BlockingQueue<Integer> c, int number) {
        this.container = c;
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }

    private void consume() {
        try {
            int value = container.take();
            System.out.println("Consumer " + this.number + " got: " + value);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
