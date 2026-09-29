# Design Rationale — Smart Library

## 1. Problem Domain

Smart Library manages book operations such as borrowing and reserving books.

The library may use different backend systems. Operations and backend technologies must be able to change independently.

## 2. Why Bridge?

The system has two independent dimensions:

1. Library operation:

    * BorrowBook
    * ReserveBook

2. Backend:

    * LocalLibraryBackend
    * CloudLibraryBackend
    * LegacyLibraryAdapter

Bridge separates these dimensions.

Without Bridge, we could create classes such as:

```text
BorrowLocal
BorrowCloud
BorrowLegacy
ReserveLocal
ReserveCloud
ReserveLegacy
```

This creates unnecessary subclass combinations.

With Bridge, a new operation or a new backend can be added independently.

## 3. Why Adapter?

The legacy library system cannot directly implement `LibraryBackend`.

Its API is:

```text
process(int, long, String)
```

while the common contract is:

```text
execute(LibraryRequest)
```

The legacy system also reports errors using status codes and `LegacyStatus` instead of the common exception mechanism.

The source class is not modified. `LegacyLibraryAdapter` wraps it and converts its input and failures into the common contract.

## 4. Why Bridge Alone Is Not Enough

Bridge separates abstraction from implementation, but it does not solve an incompatible legacy API.

`LegacyLibrarySystem` still cannot be used as a `LibraryBackend`.

Therefore, Adapter is required.

## 5. Why Adapter Alone Is Not Enough

Adapter can make the legacy system compatible, but it does not separate library operations from backend implementations.

Without Bridge, every operation could become tightly coupled to each backend.

Therefore, Adapter alone does not solve the complete architectural problem.

## 6. Complexity Module

The selected complexity module is **Dynamic Implementor Selection**.

The backend is selected at runtime from user input:

```text
local
cloud
legacy
```

The client does not directly instantiate a concrete backend.

ServiceLoader discovers backend providers dynamically.

## 7. Open/Closed Principle

For a new backend, a new provider and implementation can be added without modifying existing abstraction classes.

For a new operation, a new refined abstraction and provider can be added without modifying existing operations.

## 8. Limitation

The design uses Java `ServiceLoader`, which adds some configuration complexity because provider names must be registered in service files.

## Conclusion

Smart Library combines Bridge and Adapter for one coherent problem.

Bridge provides independent variation of operations and backends.

Adapter integrates an incompatible legacy backend.

Dynamic selection demonstrates runtime flexibility and supports the Open/Closed Principle.
