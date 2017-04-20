package concurrency.client;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumer {

    public static final int CAPACITY = 10;

    public static void main(String args[]) throws InterruptedException {
        List<Integer> sharedQueue = new LinkedList<Integer>();

        Thread t1 = new Thread(new Producer(sharedQueue), "Producer");
        t1.start();
        new Thread(new Consumer(sharedQueue), "Consumer").start();

        t1.join();
        System.err.println("Ready");
    }
}
