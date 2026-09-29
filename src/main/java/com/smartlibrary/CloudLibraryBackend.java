package com.smartlibrary;

public class CloudLibraryBackend implements LibraryBackend {

    @Override
    public OperationResult execute(LibraryRequest request) {

        return OperationResult.success(
                "Cloud backend: " +
                        request.action() +
                        " completed for " +
                        request.bookId()
        );
    }
}