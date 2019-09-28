package com.example.garbagecollector.domain.repository;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

public class RepositoryProvider {

    private static JsonRepository repository;

    private RepositoryProvider() {

    }

    @NonNull
    public static JsonRepository getJsonRepository() {
        if (repository == null) {
            repository = new DefaultJsonRepository();
        }

        return repository;
    }

    public static void setRepository(JsonRepository repository) {
        RepositoryProvider.repository = repository;
    }

    @MainThread
    public static void init() {
        repository = new DefaultJsonRepository();
    }
}
