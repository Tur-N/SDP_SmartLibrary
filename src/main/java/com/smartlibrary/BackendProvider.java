package com.smartlibrary;

public interface BackendProvider {

    String key();

    LibraryBackend create();
}