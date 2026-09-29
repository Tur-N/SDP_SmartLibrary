package com.smartlibrary;

public class LocalLibraryBackend implements LibraryBackend {

    @Override
    public OperationResult execute(LibraryRequest request) {

        if (request.bookId().equals("B404")) {
            throw new LibraryOperationException("Book not found");
        }

        return OperationResult.success(
                "Local backend: " +
                        request.action() +
                        " completed for " +
                        request.bookId()
        );
    }
}