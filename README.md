# Message Queue System

This project implements a basic in-memory message queuing system, including producers, consumers, and a queue mediator. It's designed to demonstrate message publishing and consuming with a simple architectural approach.

## Features

- **Producers** can publish messages to specific topics.
- **Consumers** can subscribe to topics and receive messages as they are published.
- A **mediator** handles message routing between producers and consumers.

## Project Structure

- `src/main/scala`
    - **models**: Contains core classes for the message entity.
        - `Message.scala`: Represents a generic message container.
    - **implementation**: Implementations of interfaces.
        - `ProducerImpl.scala`: Implements producer functionalities.
        - `ConsumerImpl.scala`: Implements consumer functionalities with threading capabilities.
        - `QueueMediatorImpl.scala`: Centralized message routing between producers and consumers.
    - **interface**: Defines core interfaces for producers, consumers, and queue handling.
        - `Producer.scala`: Interface for producer components.
        - `Consumer.scala`: Interface for consumer components.
        - `QueueMediator.scala`: Interface for mediating message distribution.
    - **service**: Service facilitating producer and consumer creation and management.
        - `QueueServiceImpl.scala`: Singleton service for accessing queue functionalities.

## Installation

### Prerequisites

- [Scala](https://www.scala-lang.org/download/) (version 2.13+ recommended)
- [sbt](https://www.scala-sbt.org/download.html)

### Building the project

- compile`sbt clean compile`
- test: `sbt test`
