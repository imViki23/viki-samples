import java.util.function.Function;
import java.util.function.Predicate;

public class CustomOptional<T> {

    // For empty refer to static instance instead of creating object everytime EMPTY has to be returned
    private static final CustomOptional<?> EMPTY = new CustomOptional<>(null);
    private final T data;

    private CustomOptional(T data) {
        this.data = data;
    }
    
    public static <T> CustomOptional<T> of(T data) {
        return new CustomOptional<>(data);
    }

    @SuppressWarnings("unchecked")
    public static <T> CustomOptional<T> empty() {
        return (CustomOptional<T>) EMPTY;
    }

    public <R> CustomOptional<R> map(Function<T, R> consumerFn) {
        if (this.data == null) {
            return empty();
        }
        return CustomOptional.of(consumerFn.apply(data));
    }

    public CustomOptional<T> filter(Predicate<? super T> predicateFn) {
        if (this.data == null) {
            return empty();
        }
        return predicateFn.test(this.data) ? this : empty();
    }

    public T orElse(T fallbackdata) {
        if (this.data == null) {
            return fallbackdata;
        }
        return this.data;
    }

    public static void main(String args[]) {
        String value = CustomOptional.of("Hello world")
            .filter(val -> val.length() > 5)
            .map(String::toUpperCase)
            .orElse("Default Value");
            System.out.println(value);
    }

}