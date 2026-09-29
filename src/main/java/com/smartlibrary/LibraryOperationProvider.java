package com.smartlibrary;

public interface LibraryOperationProvider {

    String key();

    LibraryOperation create(LibraryBackend backend);
}