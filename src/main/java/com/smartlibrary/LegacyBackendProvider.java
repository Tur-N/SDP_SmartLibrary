package com.smartlibrary;

public class LegacyBackendProvider implements BackendProvider {

    @Override
    public String key() {
        return "legacy";
    }

    @Override
    public LibraryBackend create() {
        return new LegacyLibraryAdapter(
                new LegacyLibrarySystem()
        );
    }
}