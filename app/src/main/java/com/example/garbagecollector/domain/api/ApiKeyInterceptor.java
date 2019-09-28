package com.example.garbagecollector.domain.api;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class ApiKeyInterceptor implements Interceptor {


    private ApiKeyInterceptor() {}

    @NonNull
    public static Interceptor create() {
        return new ApiKeyInterceptor();
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
//        if (TextUtils.isEmpty(mToken)) {
//            Log.d("REQUEST SERVER", "TOKEN IS EMPTY");
//            return chain.proceed(chain.request());
//        }
        Log.d("REQUEST SERVER", "TOKEN IS NOT EMPTY");
        Request request = chain.request().newBuilder()
//                .addHeader("Authorization", String.format("%s %s", "Bearer", mToken))
                .build();
//        Request request = chain.request().newBuilder()
//                .addHeader("Authorization", String.format("%s %s", "Bearer", BuildConfig.API_KEY))
//                .build();
        return chain.proceed(request);

//        Request request = chain.request().newBuilder()
//                .addHeader("Authorization", String.format("%s %s", "Bearer", mToken))
//                .addHeader("Accept", "application/json, text/plain, */*")
//                .addHeader("Content-Type", "application/json;charset=utf-8")
//                .build();
//        return chain.proceed(request);
    }
}
