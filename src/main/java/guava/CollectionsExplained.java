package guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.List;

//  https://code.google.com/p/guava-libraries/wiki/CollectionUtilitiesExplained
//  https://code.google.com/p/guava-libraries/wiki/FunctionalExplained
//  https://code.google.com/p/guava-libraries/wiki/OrderingExplained
public class CollectionsExplained {

    public static void main(String[] args) {
        CollectionsExplained explained = new CollectionsExplained();

        List<String> strings = Lists.newArrayList("one_A", "two", null, "four_aaa", null, "six", "a");
        List<String> result = explained.filterALettersAndTransformUpperCase(strings);
        System.err.println(result);

        List<Integer> numbers = Lists.newArrayList(3, 2, 5, 4, 1, 6, 9, 8, 7, 0);
        Ordering<Integer> natural = Ordering.natural();
        System.err.println(natural.sortedCopy(numbers));

        Ordering<Integer> reversed = natural.reverse();
        System.err.println(reversed.sortedCopy(numbers));

        Ordering<Integer> function = natural.onResultOf(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer input) {
                return input > 5 ? input * 2 : input * 6;
            }
        });
        System.err.println(function.sortedCopy(numbers));

    }

    public List<String> filterALettersAndTransformUpperCase(List<String> strings) {
        return FluentIterable
                .from(strings)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean apply(String input) {
                        return input != null && (input.contains("a") || input.contains("A"));
                    }
                })
                .transform(new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return input.toUpperCase();
                    }
                })
                .toList();
    }

}
