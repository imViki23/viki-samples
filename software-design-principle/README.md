## SOLID principle

- *Single Responsibility Principle (SRP)* - A class should have only one reason to change, meaning it should have only one job or responsibility. e.g., a class that handles user authentication should not also handle user data storage.
- *Open/Closed Principle (OCP)* - Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification. For example if you want to add new payment methods to a payment processing system, you should be able to do so by creating new classes that implement a common interface, rather than modifying existing classes.
- *Liskov Substitution Principle (LSP)* - Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program. For example, if you have a class `Bird` and a subclass `Penguin`, you should be able to use a `Penguin` object wherever a `Bird` object is expected without causing errors or unexpected behavior.
- *Interface Segregation Principle (ISP)* - Clients should not be forced to depend on interfaces they do not use. For example, if you have an interface `Vehicle` with methods `startEngine()`, `fly()`, and `sail()`, a class `Car` should not be forced to implement the `fly()` and `sail()` methods if it cannot fly or sail.
- *Dependency Inversion Principle (DIP)* - High-level modules should not depend on low-level modules. Both should depend on abstractions (e.g., interfaces). For example, a `PaymentProcessor` class should depend on an `IPaymentMethod` interface rather than concrete classes like `CreditCardPayment` or `PayPalPayment`. This allows for easier swapping of payment methods without modifying the high-level module.

## SAGA pattern

- In a microservices architecture, services maintain strict database isolation. For example, the Order Service should only access order-related tables, and the Shipping Service should only access shipping-related tables.
- However, what happens when a transaction fails midway—such as a payment failure—and you need to roll back changes made in the shipping tables? Because the Order Service cannot directly access the Shipping Service's database, the Shipping Service must expose a dedicated API (acting as a compensating transaction) that safely performs the necessary 'undo' operation.
- Two way to implement
    - *Choreography* - Services talk directly to each other by listening to and publishing events/messages (e.g., via Kafka, RabbitMQ). There is no central controller.
    - *Orchestration* - A central SAGA Orchestrator (e.g., using Temporal, Camunda, or a dedicated Spring Boot workflow) tells each service what local transaction to execute next and handles trigger compensation logic. Chore.