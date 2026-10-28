**Inversion of Control (IoC)** and **SAGA** belong to slightly different levels of software engineering design, but they are both core architectural and structural patterns.

To make sense of how software design concepts fit together, engineers typically group them into three main layers: **Core Object-Oriented Principles**, **Design Patterns (Micro)**, and **Architectural Patterns & Principles (Macro)**.

---

## 1. Core Object-Oriented Principles (The Foundation)

These are fundamental guidelines for writing maintainable, scalable code within a single application or class structure.

### SOLID Principles

* **S — Single Responsibility Principle (SRP):** A class should have one, and only one, reason to change.
* **O — Open/Closed Principle (OCP):** Software entities should be **open for extension**, but **closed for modification**.
* **L — Liskov Substitution Principle (LSP):** Subtypes must be substitutable for their base types without altering program correctness.
* **I — Interface Segregation Principle (ISP):** Clients should not be forced to depend on interfaces they do not use (prefer small, focused interfaces).
* **D — Dependency Inversion Principle (DIP):** High-level modules should not depend on low-level modules; both should depend on abstractions. *(This is the principle behind Spring IoC)*.

### Other Foundational Principles

* **DRY (Don't Repeat Yourself):** Avoid duplicating business logic across different parts of the system.
* **KISS (Keep It Simple, Stupid):** Avoid over-engineering; choose simple implementations over complex ones unless necessity demands it.
* **YAGNI (You Aren't Gonna Need It):** Don't implement features or abstractions until they are actually required.
* **Separation of Concerns (SoC):** Divide a program into distinct sections, where each section addresses a separate feature or concern (e.g., UI layer vs. Business Logic vs. Data Access).

---

## 2. Micro-Level Design Patterns (Class & Object Level)

These are reusable solutions to recurring object-oriented coding problems (popularized by the Gang of Four).

| Category | Examples | Typical Use Case |
| --- | --- | --- |
| **Creational** | Factory, Singleton, Builder, Prototype | Abstracting the instantiation process so code isn't dependent on `new` calls everywhere. |
| **Structural** | Adapter, Decorator, Facade, Proxy | Composition of classes/objects to form larger structures without tight coupling. |
| **Behavioral** | Observer, Strategy, Command, State | Defining communication and responsibility distribution between objects. |

---

## 3. Macro & Architectural Patterns (System & Microservices Level)

Where **IoC** sits at the component/framework level and **SAGA** sits at the distributed transaction level, these patterns govern how large systems are structured and decoupled.

### Distributed Data & Transaction Patterns

* **SAGA Pattern:** Manages distributed transactions across microservices using a sequence of local transactions and compensating actions (Orchestration vs. Choreography).
* **CQRS (Command Query Responsibility Segregation):** Separates read operations (queries) from write operations (commands) for optimized scaling and distinct data models.
* **Event Sourcing:** Instead of storing just the current state of an entity, store the full sequence of state-changing events in an append-only log.
* **Outbox Pattern:** Guarantees reliable event publishing alongside local database updates within a microservice.

### System Boundary & Architecture Principles

* **Domain-Driven Design (DDD):** Designing system boundaries based on real-world business domains (Bounded Contexts, Aggregates, Ubiquitous Language).
* **Hexagonal Architecture (Ports and Adapters):** Decouples core business logic from external frameworks, databases, and APIs so the domain logic remains pure and testable.
* **Event-Driven Architecture (EDA):** Components communicate asynchronously by emitting and listening to events rather than using synchronous REST/gRPC calls.
* **Circuit Breaker:** Prevents cascading failures in distributed systems by tripping when a downstream service fails repeatedly.

---

## Summary Comparison

```
+-----------------------------------------------------------------------+
| Architectural / Distributed Patterns (e.g., SAGA, CQRS, EDA)          |
+-----------------------------------------------------------------------+
| Design Patterns & Framework Mechanics (e.g., IoC, Factory, Observer)  |
+-----------------------------------------------------------------------+
| Fundamental Principles (e.g., SOLID, DRY, Separation of Concerns)     |
+-----------------------------------------------------------------------+

```

* **IoC** is a design principle implemented via mechanisms like Dependency Injection to decouple class dependencies.
* **SAGA** is an architectural pattern used to manage distributed state across independent microservices.