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