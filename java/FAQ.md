# FAQ

## Functional interface ?

*Before Java 8* - Java did not support passing methods or blocks of logic directly. To pass behavior into a method (such as a callback or a sorting comparator), developers had to wrap that logic inside an anonymous inner class—essentially creating a temporary object just to carry a single method.

*From Java 8 (The Functional Era)* -  The introduction of lambda expressions and functional interfaces allowed developers to pass behavior directly.

- `Runnable` - `void run()`
- `Predicate` - `boolean test(T arg)`

## Types of JVM process memory

- Heap (Young Gen - Eden space and Survivor space S0 and S1, Old / Tenured Gen)
- Metaspace - Starting with Java 8, PermGen was completely removed and replaced by Metaspace, which resides in Native Memory (Off-Heap) managed directly by the operating system, rather than the JVM heap. By default, Metaspace dynamically grows as needed up to the limit of the host OS native memory. By default, Metaspace dynamically grows as needed up to the limit of the host OS native memory.

## Garbage collection lifecycle

- New object creations are first allocated continuously in Eden.
- When Eden fills up, the JVM triggers a *Minor GC*.
- The Garbage Collector scans Eden and the active Survivor space (S0).
- Live (referenced) objects are copied into the empty Survivor space (S1).
- Dead objects in Eden and S0 are immediately discarded (memory is reclaimed in bulk).
- The age (tenure count) of surviving objects is incremented by 1.
- S1 now becomes the active "From" space, and S0 becomes the empty "To" space for the next Minor GC cycle.
- Every object has an age counter. Once an object survives a specific number of Minor GC sweeps (controlled by -XX:MaxTenuringThreshold, default up to 15), it is promoted to the Old Generation. Also if an object is too large to fit into Eden (e.g., a massive byte array), the JVM allocates it directly in the Old Generation to avoid heavy copying overhead.
- When the Old Generation becomes full, the JVM executes a Major GC (or Full GC).
- Major GC uses mark-sweep-compact algorithms to clean up unreferenced objects and compact memory to eliminate fragmentation.
- Major GC pauses are significantly longer than Minor GC pauses because the Old Generation is much larger and contains complex reference chains.
- JVM options to control
    - `-XX:NewRatio=2` - Sets the ratio between Young and Old Generation. A ratio of 2 means Young:Old is 1:2 (Young Gen is 33% of total heap).
    - `-XX:SurvivorRatio=8` - Sets the ratio between Eden and a single Survivor space (e.g., 8 means Eden is 8x the size of S0/S1).
    - `-XX:MaxTenuringThreshold=15` - Sets the maximum age before an object is promoted to the Old Generation.

## SAGA pattern

In a traditional monolithic application, maintaining data consistency is easy: you use a single database and rely on ACID transactions (Atomicity, Consistency, Isolation, Durability) with a simple BEGIN TRANSACTION and COMMIT or ROLLBACK.

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