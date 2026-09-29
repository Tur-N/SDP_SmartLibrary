package com.smartlibrary;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public final class BackendSelector {

    private BackendSelector() {
    }

    public static LibraryBackend select(String key) {

        Map<String, BackendProvider> providers =
                new HashMap<>();

        for (BackendProvider provider :
                ServiceLoader.load(BackendProvider.class)) {

            providers.put(provider.key().toLowerCase(), provider);
        }

        BackendProvider provider =
                providers.get(key.toLowerCase());

        if (provider == null) {
            throw new IllegalArgumentException(
                    "Unknown backend: " + key
            );
        }

        return provider.create();
    }
}