package com.smartlibrary;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public final class ActionSelector {

    private ActionSelector() {
    }

    public static LibraryOperation select(
            String key,
            LibraryBackend backend) {

        Map<String, LibraryOperationProvider> providers =
                new HashMap<>();

        for (LibraryOperationProvider provider :
                ServiceLoader.load(
                        LibraryOperationProvider.class)) {

            providers.put(
                    provider.key().toLowerCase(),
                    provider
            );
        }

        LibraryOperationProvider provider =
                providers.get(key.toLowerCase());

        if (provider == null) {
            throw new IllegalArgumentException(
                    "Unknown action: " + key
            );
        }

        return provider.create(backend);
    }
}