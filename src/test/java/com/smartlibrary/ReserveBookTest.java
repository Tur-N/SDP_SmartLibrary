package com.smartlibrary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReserveBookTest {

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
    void reserveDelegatesToBackend() {

        StubBackend stub = new StubBackend();

        ReserveBook reserve =
                new ReserveBook(stub);

        OperationResult result =
                reserve.execute(
                        "M202",
                        "B2002"
                );

        assertTrue(result.success());

        assertEquals(
                LibraryRequest.ActionType.RESERVE,
                stub.received.action()
        );

        assertEquals(
                "M202",
                stub.received.memberId()
        );

        assertEquals(
                "B2002",
                stub.received.bookId()
        );
    }
}