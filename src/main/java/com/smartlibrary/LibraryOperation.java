package com.smartlibrary;

public abstract class LibraryOperation {

    protected final LibraryBackend backend;

    protected LibraryOperation(LibraryBackend backend) {
        this.backend = backend;
    }

    protected LibraryRequest request(
            LibraryRequest.ActionType action,
            String memberId,
            String bookId) {

        return new LibraryRequest(action, memberId, bookId);
    }

    public abstract OperationResult execute(
            String memberId,
            String bookId);
}