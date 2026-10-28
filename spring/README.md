# FAQs

## What is spring? 

Spring is a enterprise application framework. It provides a lot of helper classes, utilities, annotations, etc., which helps developers to build microservices, web applications etc.

## What is Spring Boot?

Spring Boot is a framework built on top of Spring. It adds features like embedded servers and auto-configuration, which help us in reducing a lot of boilerplate code.

## What is auto configuration? 

Spring Boot provides auto configuration via starters. There are many starters for different technologies, for Postgres, for Actuator, and that stuff. If we don't have these starters or auto configuration, you have to define the bean by yourself. Here, the Spring Boot team has defined the bean for us, and the properties of the bean can be customized using application.properties.

## What is IOC in Spring? 

Inversion of Control (IoC) is a design principle where the control of object creation and dependency management is inverted—transferred from your application code to an external container (in this case, the Spring IoC Container).

Instead of Car creating its own Engine, the Spring IoC Container creates the Engine and injects (passes) it into the Car at runtime. This is also known as Dependency Injection (DI).

## SAGA pattern

- In a microservices architecture, services maintain strict database isolation. For example, the Order Service should only access order-related tables, and the Shipping Service should only access shipping-related tables.
- However, what happens when a transaction fails midway—such as a payment failure—and you need to roll back changes made in the shipping tables? Because the Order Service cannot directly access the Shipping Service's database, the Shipping Service must expose a dedicated API (acting as a compensating transaction) that safely performs the necessary 'undo' operation.
- Two way to implement
    - *Choreography* - Services talk directly to each other by listening to and publishing events/messages (e.g., via Kafka, RabbitMQ). There is no central controller.
    - *Orchestration* - A central SAGA Orchestrator (e.g., using Temporal, Camunda, or a dedicated Spring Boot workflow) tells each service what local transaction to execute next and handles trigger compensation logic. Chore.