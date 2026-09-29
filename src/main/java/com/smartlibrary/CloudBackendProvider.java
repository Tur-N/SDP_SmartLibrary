package com.smartlibrary;

public class CloudBackendProvider implements BackendProvider {

    @Override
    public String key() {
        return "cloud";
    }

    @Override
    public LibraryBackend create() {
        return new CloudLibraryBackend();
    }
}