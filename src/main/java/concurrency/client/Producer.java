package concurrency.client;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {

    private final List<Integer> sharedQueue;

    public Producer(List<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int i = ThreadLocalRandom.current().nextInt(0, 1000);
                produce(i);
                System.out.println("Produced: " + i);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void produce(int i) throws InterruptedException {
        // wait if queue is full
        while (sharedQueue.size() == ProducerConsumer.CAPACITY) {
            synchronized (sharedQueue) {
                System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: "
                        + sharedQueue.size());

                sharedQueue.wait();
            }
        }

        // producing element and notify consumers
        synchronized (sharedQueue) {
            sharedQueue.add(i);
            sharedQueue.notifyAll();
        }
    }
}
