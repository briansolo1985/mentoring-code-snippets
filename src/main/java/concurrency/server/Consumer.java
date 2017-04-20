package concurrency.server;

public class Consumer extends Thread {
    private ThreadSafeContainer container;
    private int number;

    public Consumer(ThreadSafeContainer c, int number) {
        container = c;
        this.number = number;
    }

    @Override
    public void run() {
        int value = 0;
        while (true) {
            value = container.get();
            System.out.println("Consumer #" + this.number + " got: " + value);
        }
    }
}
