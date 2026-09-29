package com.smartlibrary;

public class ReserveOperationProvider
        implements LibraryOperationProvider {

    @Override
    public String key() {
        return "reserve";
    }

    @Override
    public LibraryOperation create(
            LibraryBackend backend) {

        return new ReserveBook(backend);
    }
}