package com.smartlibrary;

public class BorrowOperationProvider
        implements LibraryOperationProvider {

    @Override
    public String key() {
        return "borrow";
    }

    @Override
    public LibraryOperation create(
            LibraryBackend backend) {

        return new BorrowBook(backend);
    }
}