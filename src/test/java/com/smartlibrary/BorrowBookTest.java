package com.smartlibrary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowBookTest {

    static class StubBackend
            implements LibraryBackend {

        LibraryRequest received;

        @Override
        public OperationResult execute(
                LibraryRequest request) {

            received = request;

            return OperationResult.success(
                    "stub success"
            );
        }
    }

    @Test
    void borrowDelegatesToBackend() {

        StubBackend stub = new StubBackend();

        BorrowBook borrow =
                new BorrowBook(stub);

        OperationResult result =
                borrow.execute(
                        "M101",
                        "B1001"
                );

        assertTrue(result.success());

        assertEquals(
                LibraryRequest.ActionType.BORROW,
                stub.received.action()
        );

        assertEquals(
                "M101",
                stub.received.memberId()
        );

        assertEquals(
                "B1001",
                stub.received.bookId()
        );
    }
}