package com.example.garbagecollector.domain.api;

import androidx.annotation.NonNull;

import com.example.garbagecollector.BuildConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class StandardApiFactory {

//    private static

    private static OkHttpClient sClient;

    private static volatile JsonService sService;





    private StandardApiFactory() {
    }

    @NonNull
    public static JsonService getJsonService() {
        JsonService service = sService;
        if (service == null) {
            synchronized (StandardApiFactory.class) {
                service = sService;
                if (service == null) {
                    service = sService = buildRetrofit().create(JsonService.class);
                }
            }
        }
        return service;
    }

    public static void recreate() {
        sClient = null;
        sClient = getClient();
        sService = buildRetrofit().create(JsonService.class);
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (StandardApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
            return new OkHttpClient.Builder()
                    .addInterceptor(LoggingInterceptor.create())
                    .build();
    }

}
