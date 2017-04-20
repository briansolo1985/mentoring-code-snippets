package concurrency2.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

    private final ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        new FutureExample().start();
    }

    public void start() {
        Future<String> future = service.submit(new Communicator());

        String localResult = localHeavyWeightProcess();
        String remoteResult = null;
        try {
            remoteResult = future.get();
        } catch (InterruptedException | ExecutionException e) {
        }

        System.err.println(remoteResult + localResult);
    }

    private String localHeavyWeightProcess() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.err.println("Local ready");
        return " pussy bitch";
    }
}
