package guava;

import com.google.common.base.Optional;

// https://code.google.com/p/guava-libraries/wiki/UsingAndAvoidingNullExplained
public class OptionalExample {

    public static void main(String[] args) {
        OptionalExample example = new OptionalExample();

        Optional<String> result = example.getValue(false);
        if (result.isPresent()) {
            System.err.println(result.get());
        } else {
            System.err.println("null");
        }

    }

    public Optional<String> getValue(boolean condition) {
        String value = null;
        if (condition) {
            value = "not null";
        }
        return Optional.<String>fromNullable(value);
    }

}
