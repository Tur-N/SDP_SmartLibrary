package com.smartlibrary;

public class LocalBackendProvider implements BackendProvider {

    @Override
    public String key() {
        return "local";
    }

    @Override
    public LibraryBackend create() {
        return new LocalLibraryBackend();
    }
}