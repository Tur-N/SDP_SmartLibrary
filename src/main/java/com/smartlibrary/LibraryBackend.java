package com.smartlibrary;

public interface LibraryBackend {
    OperationResult execute(LibraryRequest request);
}