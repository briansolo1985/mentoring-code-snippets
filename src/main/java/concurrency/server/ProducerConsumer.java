package concurrency.server;

public class ProducerConsumer {
    public static void main(String[] args) {
        ThreadSafeContainer c = new ThreadSafeContainer();

        new Producer(c, 1).start();
        new Consumer(c, 1).start();

        System.err.println("Ready");
    }
}