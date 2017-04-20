package concurrency2.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {

    private static final int CAPACITY = 10;

    private final ExecutorService executorService;
    private final ThreadSafeCollection sharedObject;

    public ProducerConsumer() {
        executorService = Executors.newCachedThreadPool();
        sharedObject = new ThreadSafeCollection(CAPACITY);
    }

    public static void main(String[] args) {
        new ProducerConsumer().start();
    }

    public void start() {
        executorService.execute(new Producer(sharedObject));
        executorService.execute(new Producer(sharedObject));
        executorService.execute(new Consumer(sharedObject));
    }
}
