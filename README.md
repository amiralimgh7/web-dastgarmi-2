# Form Management API

A Spring Boot REST API for creating forms, managing their fields, and publishing forms. Data is stored with Spring Data JPA in an in-memory H2 database.

## Stack

Java 17, Spring Boot, Spring Web, Spring Data JPA, H2, Lombok, and Maven.

## Getting started

Use JDK 17 and the included Maven wrapper:

```sh
./mvnw spring-boot:run
```

On Windows, use `mvnw.cmd spring-boot:run`. The application uses the default Spring Boot port, 8080. Its database is in memory, so data is lost when the application stops.

## Endpoints

| Method | Path | Action |
| --- | --- | --- |
| GET | `/forms` | List forms |
| POST | `/forms` | Create a form |
| GET | `/forms/{id}` | Get a form |
| PUT | `/forms/{id}` | Update form properties |
| DELETE | `/forms/{id}` | Delete a form |
| GET | `/forms/{id}/fields` | List a form's fields |
| PUT | `/forms/{id}/fields` | Replace a form's fields |
| POST | `/forms/{id}/publish` | Publish a form |
| GET | `/forms/published` | List published forms |

## Project layout

| Path | Purpose |
| --- | --- |
| `src/main/java/com/example/demo/controller/` | REST endpoints |
| `src/main/java/com/example/demo/model/` | Form and field entities |
| `src/main/java/com/example/demo/repository/` | JPA repositories |
| `src/main/java/com/example/demo/exception/` | Exception types and handler |
| `src/main/resources/application.properties` | Database and application settings |
| `docs/report.pdf` | Original project report |
| `docs/snapshots/` | Original source archive |

## Checks

```sh
./mvnw test
```
