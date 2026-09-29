# Smart Library

## Overview

Smart Library is a Java project demonstrating the **Bridge** and **Adapter** design patterns in one system.

The system supports two library operations:

* Borrow book
* Reserve book

It supports three backend implementations:

* Local backend
* Cloud backend
* Legacy backend through Adapter

## Patterns

### Bridge

`LibraryOperation` is the Abstraction.

Refined Abstractions:

* `BorrowBook`
* `ReserveBook`

Implementor:

* `LibraryBackend`

Implementations:

* `LocalLibraryBackend`
* `CloudLibraryBackend`
* `LegacyLibraryAdapter`

### Adapter

`LegacyLibrarySystem` uses an incompatible API:

```text
process(int, long, String)
```

It returns `LegacyStatus` instead of using the normal backend contract.

`LegacyLibraryAdapter` converts the legacy API to:

```text
execute(LibraryRequest)
```

All legacy failures are translated into `LibraryOperationException`.

## Dynamic Selection

The user chooses both operation and backend from runtime input.

Example:

```text
borrow M101 B1001 local
```

```text
reserve M101 B1001 cloud
```

```text
borrow M101 B1001 legacy
```

The client does not hard-code concrete backend classes.

## Requirements

Java 17+
Maven

## Build and test

```bash
mvn clean test
```

## Run

```bash
mvn exec:java -Dexec.mainClass="com.smartlibrary.Main"
```

Example input:

```text
borrow M101 B1001 legacy
```

## OCP

A new backend can be added through a new `BackendProvider` without modifying `LibraryOperation`, existing backends, or the client.

A new library operation can be added through a new `LibraryOperationProvider` without modifying existing operations.

## Files

* `UML.puml` - UML class diagram
* `design-rationale.md` - design explanation
* `src/test` - JUnit 5 tests
