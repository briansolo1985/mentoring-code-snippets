package java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIO {

    private static final String SOURCE_PATH = "src/main/resources/com/briansolo/java8tuts/example1.txt";
    private static final String NF = "NOT_FOUND";

    public static void main(String[] args) throws IOException {
        Predicate<String> firstTenLengthWord = s -> s.length() == 10;
        findFirstMatch(firstTenLengthWord);

        Predicate<String> firstSixLengthWordContainingABC = combine(
                s -> s.length() == 6, s -> s.contains("a"),
                s -> s.contains("b"), s -> s.contains("c"));
        findFirstMatch(firstSixLengthWordContainingABC);

        Predicate<String> isOOWordContainsBLongerThan5 = combine(
                s -> s.length() > 5, s -> s.contains("b"), FileIO::isOOWord);
        findFirstMatch(isOOWordContainsBLongerThan5);

        Function<Stream<String>, List<String>> wordsLongerThan4SortedWithExclamationMarkAndUpperCased = stream -> stream
                .filter(s -> s.length() > 4).map(s -> s.toUpperCase() + "!")
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList());
        System.err.println(executeLines(SOURCE_PATH,
                wordsLongerThan4SortedWithExclamationMarkAndUpperCased));

        Function<Stream<Path>, Long> countFiles = stream -> stream.count();
        System.err.println(executeWalk(".", countFiles));

        List<Double> formattedDoubles = Stream.generate(Math::random)
                .map(d -> d * 100).map(d -> String.format("%.3f", d))
                .map(s -> s.replace(",", ".")).map(Double::parseDouble)
                .limit(17).collect(Collectors.toList());
        System.err.println(formattedDoubles);

    }

    private static boolean isOOWord(String s) {
        return s.contains("OO");
    }

    @SafeVarargs
    private static <T> Predicate<T> combine(Predicate<T>... predicates) {
        Predicate<T> combination = e -> true;
        for (Predicate<T> predicate : predicates) {
            combination = combination.and(predicate);
        }
        return combination;
    }

    private static <T> void findFirstMatch(Predicate<? super String> predicate) {
        Function<Stream<String>, String> function = stream -> stream
                .filter(predicate).findFirst().orElse(NF);

        System.err.println(executeLines(SOURCE_PATH, function));
    }

    private static <T> T executeLines(String path,
                                      Function<Stream<String>, T> function) {
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            return function.apply(toWords(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<String> toWords(Stream<String> lines) {
        return lines.map(s -> Arrays.asList(s.split(","))).flatMap(
                sa -> sa.stream());
    }

    private static <T> T executeWalk(String path,
                                     Function<Stream<Path>, T> function) {
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            return function.apply(files);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
