# FAQ

## Functional interface

*Before Java 8* - Java did not support passing methods or blocks of logic directly. To pass behavior into a method (such as a callback or a sorting comparator), developers had to wrap that logic inside an anonymous inner class—essentially creating a temporary object just to carry a single method.

*From Java 8 (The Functional Era)* -  The introduction of lambda expressions and functional interfaces allowed developers to pass behavior directly.

- `Runnable` - `void run()`
- `Predicate` - `boolean test(T arg)`

## Types of JVM process memory

- *Heap* - To store objects
    - Young Gen
        - Eden space
        - Survivor space S0 and S1
    - Old / Tenured Gen
- *Metaspace* - Starting with Java 8, PermGen was completely removed and replaced by Metaspace, which resides in Native Memory (Off-Heap) managed directly by the operating system, rather than the JVM heap. By default, Metaspace dynamically grows as needed up to the limit of the host OS native memory.

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

- In a microservices architecture, services maintain strict database isolation. For example, the Order Service should only access order-related tables, and the Shipping Service should only access shipping-related tables.
- However, what happens when a transaction fails midway—such as a payment failure—and you need to roll back changes made in the shipping tables? Because the Order Service cannot directly access the Shipping Service's database, the Shipping Service must expose a dedicated API (acting as a compensating transaction) that safely performs the necessary 'undo' operation.
- Two way to implement
    - *Choreography* - Services talk directly to each other by listening to and publishing events/messages (e.g., via Kafka, RabbitMQ). There is no central controller.
    - *Orchestration* - A central SAGA Orchestrator (e.g., using Temporal, Camunda, or a dedicated Spring Boot workflow) tells each service what local transaction to execute next and handles trigger compensation logic. Chore.