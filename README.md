# FAQ

## What is Functional interface ?

Java needed a way to pass behavior (methods) around without forcing developers to write verbose anonymous classes.

- `Runnable` - `void run()`
- `Predicate` - `boolean test(T arg)`


## About usage of generics

When a generic type parameter <T> is declared at the class level, it is automatically available to all non-static methods in that class. However, if you place <T> again right after public in a method signature, you are declaring a method-level type parameter. This creates a brand-new, independent generic type that completely shadows (hides) the class-level T, causing compiler conflicts if you try to mix them. To use the class-level type, simply omit the angle brackets in the method signature.

```java
public class CustomOptional<T> {

    public <R> CustomOptional<R> map(Function<T, R> consumerFn) {
        if (this.data == null) {
            return empty();
        }
        return CustomOptional.of(consumerFn.apply(data));
    }

    // filter should not have <T> after public
    public <T> CustomOptional<T> filter(Predicate<? super T> predicateFn) {
        if (this.data == null) {
            return empty();
        }
        return predicateFn.test(this.data) ? this : empty();
    }

}
```

## What is VM option in Java

## What is -X And -XX

## Command to list all Java process

- `jps -l`
- `ps -ef | grep java`