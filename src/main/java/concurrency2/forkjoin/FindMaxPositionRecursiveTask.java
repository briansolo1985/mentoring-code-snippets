package concurrency2.forkjoin;

import java.util.concurrent.RecursiveTask;

public class FindMaxPositionRecursiveTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 10000;
    private int[] data;
    private int start;
    private int end;

    public FindMaxPositionRecursiveTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        return isIntervalSmallEnough() ? maxValue() : forkJoin();
    }

    private boolean isIntervalSmallEnough() {
        return end - start <= THRESHOLD;
    }

    private Integer maxValue() {
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    private Integer forkJoin() {
        int halfWay = ((end - start) / 2) + start;

        FindMaxPositionRecursiveTask t1 = new FindMaxPositionRecursiveTask(data, start, halfWay);
        t1.fork();

        FindMaxPositionRecursiveTask t2 = new FindMaxPositionRecursiveTask(data, halfWay, end);

        int maxA = t2.compute();
        int maxB = t1.join();

        return maxA > maxB ? maxA : maxB;
    }
}