package guava;

import com.google.common.base.Preconditions;

// https://code.google.com/p/guava-libraries/wiki/PreconditionsExplained
public class PreconditionsExample {

    private Integer a = 0;

    public static void main(String[] args) {
        PreconditionsExample example = new PreconditionsExample();
        example.method(null);
        example.method(10);
        example.method(3);
    }

    public void method(Integer a) {
        Preconditions.checkNotNull(a, "can not be null");
        Preconditions.checkArgument(a > 0 && a < 10, "must be between zero and ten exclusive");
        this.a = a * 2;
        Preconditions.checkState(this.a != 6, "a can not be six");
    }
}
