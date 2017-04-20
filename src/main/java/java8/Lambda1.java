package java8;

import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Lambda1 {

    private String[] forSort = {"harry", "voldemort", "albus", "snape",
            "severus", "lupin", "ron", "hermione", "", null};

    private static boolean invalid(String s1, String s2) {
        return Strings.isNullOrEmpty(s1) || Strings.isNullOrEmpty(s2);
    }

    private static int eFirst(String s1, String s2) {
        return invalid(s1, s2) || !s1.matches("^.*[e]+.*$") ? 1 : -1;
    }

    private static <T> T betterElement(T s1, T s2,
                                       TwoElementsPredicate<T> predicate) {
        return predicate.decide(s1, s2) ? s1 : s2;
    }

    public static void main(String[] args) {
        Comparator<String> byLength = (s1, s2) -> invalid(s1, s2)
                || s1.length() < s2.length() ? -1
                : s1.length() == s2.length() ? 0 : 1;

        Comparator<String> byLexicoFirstCharOnly = (s1, s2) -> invalid(s1, s2) ? -1
                : s1.substring(0, 1).compareToIgnoreCase(s2.substring(0, 1));

        Comparator<String> containsEFirst = (s1, s2) -> invalid(s1, s2)
                || !s1.matches("^.*[e]+.*$") ? 1 : -1;

        Lambda1 lambda1 = new Lambda1();

        lambda1.print();
        lambda1.sort(byLength);
        lambda1.print();
        lambda1.sort(Collections.reverseOrder(byLength));
        lambda1.print();

        lambda1.sort(byLexicoFirstCharOnly);
        lambda1.print();
        lambda1.sort(containsEFirst);
        lambda1.print();

        lambda1.sort(Lambda1::eFirst);
        lambda1.print();

        System.err.println(betterElement("lex", "luthor", (s1, s2) -> true));
    }

    private void print() {
        System.err.println(Arrays.toString(forSort));
    }

    private void sort(Comparator<String> comparator) {
        Arrays.sort(forSort, comparator);
    }

    @FunctionalInterface
    interface TwoElementsPredicate<T> {
        boolean decide(T s1, T s2);
    }

}
