package concurrency2.forkjoin;

import java.util.Date;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {

    private static final int BIG_LENGTH = 1_000_000_000;
    private static final int[] FEW_NUMBERS = {1, 3, 5, 7, 9, 2, 4, 6, 8};

    public static int[] randomNumbers(int length) {
        int[] numbers = new int[length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * length);
        }
        return numbers;
    }

    public static void main(String[] args) {
        StopWatch watch = new StopWatch();
        ForkJoinExample example = new ForkJoinExample();

        watch.start();
        System.err.println("L: Maximum value is " + example.linearSearch(FEW_NUMBERS));
        watch.stop();

        watch.start();
        System.err.println("M: Maximum value is " + example.multiLinearSearch(FEW_NUMBERS));
        watch.stop();

        int[] lotsOfNumbers = randomNumbers(BIG_LENGTH);

        watch.start();
        System.err.println("L: Maximum value is " + example.linearSearch(lotsOfNumbers));
        watch.stop();

        watch.start();
        System.err.println("M: Maximum value is " + example.multiLinearSearch(lotsOfNumbers));
        watch.stop();
    }

    public int linearSearch(int[] array) {
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    public int multiLinearSearch(int[] array) {
        return new ForkJoinPool().invoke(new FindMaxPositionRecursiveTask(array, 0, array.length));
    }

}

class StopWatch {
    private long start;

    public void start() {
        start = new Date().getTime();
    }

    public void stop() {
        System.err.println("Took " + (new Date().getTime() - start) + " ms");
    }
}
