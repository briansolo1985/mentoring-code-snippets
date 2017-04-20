package java8;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Streams1 {
    private static List<String> words = Arrays.asList("hi", "hello", "hey",
            "heyya", "bye", "byez", "higgiliho", "");
    private static List<Worker> workers = Lists.newArrayList(
            new Worker("Feri"), new Worker("Zsu"), new Worker("Alex"));

    public static void main(String[] args) {
        workers.stream().forEach(s -> s.prefix("dr."));
        workers.stream().forEach(System.out::println);

        // words.stream().forEach(e -> System.out.println("  " + e));
        // words.stream().forEach(System.out::println);

        List<String> excitingWords = words.stream().map(s -> s + "!")
                .collect(toList());
        // System.err.println(excitingWords);

        List<String> eyeWords = words.stream().filter(e -> e != null)
                .map(s -> s.replace("i", "eye")).collect(toList());
        // System.err.println(eyeWords);

        List<String> upperCaseWords = words.stream().filter(e -> e != null)
                .map(String::toUpperCase).collect(toList());
        // System.err.println(upperCaseWords);

        List<String> shortWords = words.stream().filter(s -> s.length() < 4)
                .collect(toList());
        // System.err.println(shortWords);

        List<String> wordsWithB = words.stream().filter(s -> s.contains("b"))
                .collect(toList());
        // System.err.println(wordsWithB);

        List<String> evenLengthWords = words.stream()
                .filter(s -> (s.length() % 2) == 0).collect(toList());
        // System.err.println(evenLengthWords);

        String firstString = words.stream().map(String::toUpperCase).map(s -> {
            System.err.println(s);
            return s;
        }).filter(s -> s.length() < 4).filter(s -> s.contains("E")).findFirst()
                .orElse("No match");
        // System.err.println(firstString);

        String[] excitingWordsA = words.stream().map(s -> s + "!")
                .toArray(String[]::new);
        System.err.println(Arrays.toString(excitingWordsA));
    }

    static class Worker {
        private String name;

        public Worker(String name) {
            this.name = name;
        }

        public void prefix(String prefix) {
            name = prefix + " " + name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
