package java8;

import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Streams2 {

    private static List<String> words = Arrays.asList("hi", "hello", "hey", "",
            "heyya", "bye", "byez", "higgiliho");

    private static int[] ints = {1, 3, 11, 16, 22};

    public static void main(String[] args) {
        String result = "";

        result = words.stream()
                .reduce((s1, s2) -> s1.toUpperCase() + s2.toUpperCase())
                .orElse("Empty");
        result = words.stream().map(String::toUpperCase).reduce(String::concat)
                .orElse("Empty");
        result = words.stream().map(String::toUpperCase)
                .collect(Collectors.joining(","));
        result = String.join(",", words);
        System.err.println(result);

        System.err.println(generateRandomNumbers(2));

        System.err.println(generateOrderedNumbers(24, 6, 5));

        int sumInts = -1;
        sumInts = Arrays.stream(ints).sum();
        sumInts = Ints.asList(ints).stream().reduce(0, Integer::sum);
        sumInts = Ints.asList(ints).stream().parallel().reduce(0, Integer::sum);
        System.err.println(sumInts);

        double productDouble = -1;
        List<Double> doubles = Arrays.asList(1.0, 2.0, 5.0, 7.5, 10.0, -15.0,
                -0.1, 0.4, 1.7);
        productDouble = doubles.stream().reduce(1.0, (n1, n2) -> n1 * n2);
        System.err.println(productDouble);
        productDouble = doubles.stream().parallel().reduce((n1, n2) -> n1 * n2)
                .orElse(0.0);
        System.err.println(productDouble);
    }

    private static List<Double> generateRandomNumbers(int limit) {
        return Stream.generate(Math::random).limit(limit).collect(toList());
    }

    private static List<Integer> generateOrderedNumbers(int start, int step,
                                                        int limit) {
        return Stream.iterate(start, prev -> prev + step).limit(limit)
                .collect(toList());
    }
}
