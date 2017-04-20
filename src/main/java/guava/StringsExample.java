package guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

//https://code.google.com/p/guava-libraries/wiki/StringsExplained
public class StringsExample {

    public static void main(String[] args) {
        String toSplit = "one,two, three,,five,,,seven";
        Iterable<String> splitted = Splitter.on(",").omitEmptyStrings().split(toSplit);
        System.err.println(splitted);

        String[] toJoin = {"one", "two", "three", null, "five", null, null, "seven"};
        String joined = Joiner.on(",").skipNulls().join(toJoin);
        System.err.println(joined);

        String digitsAndLetters = "123asdas3255SDAS";
        System.err.println(digitsAndLetters);

        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(digitsAndLetters, "*");
        System.err.println(noDigits);
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(digitsAndLetters);
        System.err.println(lowerAndDigit);

        String camelCase = "javaVariableName";
        String underscore = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelCase);
        System.err.println(underscore);
    }
}
