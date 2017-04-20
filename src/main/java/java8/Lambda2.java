package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lambda2 {

    private static final List<String> STRINGS = Arrays.asList("harry",
            "voldemort", "albus", "snape", "severus", "lupin", "ron",
            "hermione", "", null);

    private static final List<Integer> NUMBERS = Arrays.asList(null, 0, 1, 2,
            3, 4, 5, 6, 7, 8, 9);

    public static <T> List<T> allMatches(List<T> items, Predicate<T> predicate) {
        List<T> passed = new LinkedList<>();
        for (T item : items) {
            if (item != null && predicate.test(item)) {
                passed.add(item);
            }
        }
        return Collections.unmodifiableList(passed);
    }

    public static <I, O> List<O> transform(List<I> items,
                                           Function<I, O> function) {
        List<O> transformed = new LinkedList<>();
        for (I item : items) {
            transformed.add(item == null ? null : function.apply(item));
        }
        return Collections.unmodifiableList(transformed);
    }

    public static void main(String[] args) {
        System.err.println(allMatches(STRINGS, s -> s.length() < 4));
        System.err.println(allMatches(STRINGS, s -> s.length() % 2 == 0));
        System.err.println(allMatches(STRINGS, s -> s.contains("a")));

        System.err.println(allMatches(NUMBERS, s -> s < 5));

        System.err.println(transform(STRINGS, String::toUpperCase));
        System.err.println(transform(STRINGS, s -> s + "_#-##"));

        System.err.println(transform(STRINGS, s -> s.length()));
        System.err.println(transform(NUMBERS, s -> "$ " + s.doubleValue()));
    }
}
