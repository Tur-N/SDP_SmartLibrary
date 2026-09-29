package com.smartlibrary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LegacyLibraryAdapterTest {

    static class FailingLegacySystem
            extends LegacyLibrarySystem {

        @Override
        public LegacyStatus process(
                int memberNumber,
                long legacyBookNumber,
                String command) {

            return new LegacyStatus(
                    false,
                    404,
                    "Old system says book missing"
            );
        }
    }

    @Test
    void adapterTranslatesLegacyFailure() {

        LegacyLibraryAdapter adapter =
                new LegacyLibraryAdapter(
                        new FailingLegacySystem()
                );

        LibraryRequest request =
                new LibraryRequest(
                        LibraryRequest.ActionType.BORROW,
                        "M101",
                        "B1001"
                );

        LibraryOperationException exception =
                assertThrows(
                        LibraryOperationException.class,
                        () -> adapter.execute(request)
                );

        assertEquals(
                "Book is not available",
                exception.getMessage()
        );
    }
}