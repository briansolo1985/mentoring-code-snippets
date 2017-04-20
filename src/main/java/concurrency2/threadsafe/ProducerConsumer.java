package concurrency2.threadsafe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {
    private static final int CAPACITY = 1;

    private final ExecutorService executorService;
    private final BlockingQueue<Integer> queue;

    public ProducerConsumer(int poolSize) {
        executorService = Executors.newFixedThreadPool(poolSize);
        queue = new ArrayBlockingQueue<Integer>(CAPACITY);
    }

    public static void main(String[] args) {
        new ProducerConsumer(3).start();
    }

    public void start() {
        executorService.execute(new Producer(queue, 1));
        executorService.execute(new Consumer(queue, 1));
        executorService.execute(new Producer(queue, 2));
    }
}