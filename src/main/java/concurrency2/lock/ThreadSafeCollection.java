package concurrency2.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeCollection {

    private final Queue<Integer> queue = new LinkedList<>();

    private final Lock aLock = new ReentrantLock();
    private final Condition bufferNotFull = aLock.newCondition();
    private final Condition bufferNotEmpty = aLock.newCondition();

    private final int capacity;

    public ThreadSafeCollection(int capacity) {
        this.capacity = capacity;
    }

    public void put(int i) throws InterruptedException {
        aLock.lock();
        try {
            produce(i);
        } finally {
            aLock.unlock();
        }
    }

    public void get() throws InterruptedException {
        aLock.lock();
        try {
            consume();
        } finally {
            aLock.unlock();
        }
    }

    private void produce(int i) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println(Thread.currentThread().getName() + " : Buffer is full, waiting");
            bufferNotEmpty.await();
        }
        boolean isAdded = queue.offer(i);
        if (isAdded) {
            System.out.printf("%s added %d into queue %n", Thread.currentThread().getName(), i);
            System.out.println(Thread.currentThread().getName() + " : Signalling that buffer is no more empty now");
            bufferNotFull.signalAll();
        }
    }

    private void consume() throws InterruptedException {
        while (queue.size() == 0) {
            System.out.println(Thread.currentThread().getName() + " : Buffer is empty, waiting");
            bufferNotFull.await();
        }
        Integer value = queue.poll();
        if (value != null) {
            System.out.printf("%s consumed %d from queue %n", Thread.currentThread().getName(), value);
            System.out.println(Thread.currentThread().getName() + " : Signalling that buffer may be empty now");
            bufferNotEmpty.signalAll();
        }
    }

}
