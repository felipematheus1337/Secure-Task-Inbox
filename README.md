# Secure Task Inbox

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://adoptopenjdk.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Kafka](https://img.shields.io/badge/Kafka-CloudStream-orange.svg)](https://spring.io/projects/spring-cloud-stream)
[![Docker](https://img.shields.io/badge/Docker-Compose-lightgrey.svg)](https://www.docker.com/)
[![License](https://img.shields.io/github/license/felipematheus1337/Secure-Task-Inbox)](LICENSE)

## Overview

**Secure Task Inbox** is a secure, multi-tenant task management API built in Java using Spring Boot, designed for consuming and producing task events via Kafka with proper separation of concerns. Each task is isolated per user based on the token’s `userId`.  
This project emphasizes clean architecture, idempotent event handling, and Kafka stream processing patterns suited for real-world systems.

---

## 🚀 Key Features

✔ Multi-tenant task API scoped by authenticated user  
✔ Kafka integration using Spring Cloud Stream for producers  
✔ Kafka consumer with custom filtering and idempotent event processing  
✔ Persistent storage using MongoDB (with unique indexing)  
✔ Docker Compose for local development environment  
✔ Error handling and DLQ support in Kafka binding config

---

## 🧠 Architecture Summary

The system adopts a hybrid event handling model:

### Producer
- Uses **Spring Cloud Stream** with `StreamBridge` to produce Kafka events.
- Logical binding names (`taskCreated-out-0`, `taskStatusUpdated-out-0`) decouple topic details from code.
- DLQs are configured via application YAML, allowing automatic error handling.

### Consumer
- Uses **Spring Kafka** with a custom `@KafkaListener` and `RecordFilterStrategy`.
- A filter ensures only known event types are processed.
- Persistence is idempotent via a unique index on `eventId`.

### Event Model
Events are differentiated by their payload type, such as:
- `TaskCreateEvent`
- `TaskUpdateEvent`
- Payloads extracted and processed accordingly.

---

## 📦 Technology Stack

| Layer               | Technology                |
|--------------------|---------------------------|
| Language           | Java 17                   |
| Framework          | Spring Boot 3.x           |
| Event Streaming    | Kafka + Spring Cloud Stream |
| Persistence        | MongoDB                   |
| Serialization      | Jackson JSON              |
| Build Tool         | Maven                     |
| Orchestration      | Docker Compose            |

---

## 🚀 Getting Started

### Prerequisites

Ensure the following are installed:

- Java 17+
- Docker & Docker Compose
- Maven

### Run Locally

1. Clone the repository:

```bash
git clone https://github.com/felipematheus1337/Secure-Task-Inbox.git
cd Secure-Task-Inbox

````


2. Start dependencies with Docker Compose:
    docker compose up -d

3. Build and run the application:
   ./mvnw clean install
   ./mvnw spring-boot:run

📄 Configuration

All Kafka and application settings are defined in application.yml. Key sections include:

spring:
cloud:
stream:
kafka:
bindings:
taskCreated-in-0:
consumer:
enableDlq: true
dlqName: task-created-error-v1
taskStatusUpdated-in-0:
consumer:
enableDlq: true
dlqName: task-status-updated-error-v1

enableDlq: enables a dead-letter topic automatically without writing custom error handlers.

DLQs help isolate failed message patterns.

🛠 Idempotent Processing

Task events are stored in MongoDB with a unique eventId.

@Indexed(unique = true)
private String eventId;

This prevents reprocessing of the same event in parallel consumers or retries.

🧪 Testing

Run all unit and integration tests using:
./mvnw test
Ensure Kafka and MongoDB are running before executing integration tests.

📦 Useful Maven Commands
./mvnw clean install        # Clean and build
./mvnw spring-boot:run      # Run application
./mvnw verify -P integration # Run all integration tests

🧩 Environment Variables
Variable	Description
SPRING_PROFILES_ACTIVE	Active Spring profile
MONGODB_URI	MongoDB connection string
KAFKA_BOOTSTRAP_SERVERS	Kafka cluster connection

📜 License

All rights reserved © Felipe Matheus. Released under the MIT License.




