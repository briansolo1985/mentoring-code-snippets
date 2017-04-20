package concurrency2.future;

import java.util.concurrent.Callable;

public class Communicator implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        System.err.println("Remote ready");
        return "show your";
    }
}
