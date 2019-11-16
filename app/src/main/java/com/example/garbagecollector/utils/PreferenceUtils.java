package com.example.garbagecollector.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.garbagecollector.GarbageCollectorApp;

import org.jetbrains.annotations.NotNull;

public final class PreferenceUtils {

    //Auth
    private static final String PASSWORD_KEY = "user_password";
    private static final String TOKEN_KEY = "id_token";
    private static final String USER_NAME_KEY = "user_name";
    private static final String WALKTHROUGH_PASSED_KEY = "walkthrough_passed";
    private static final String TOKEN_DATE = "token_date";
    private static final String REST_DAYS = "rest_days";
    private static final String LOG_STATE = "log_state";
    private static final String EMAIL = "email";
    private static final String CONFIRMATION_CODE = "confirmation_code";
    //Screener
    private static final String SEX = "sex";
    private static final String USER_AGE = "user_age";
    private static final String LANGUAGE = "language";
    private static final String SCREENER = "screener_passed";
    //main flow
    private static final String DEVICE_TYPE = "device_type";
    private static final String NOTIFYING = "notifying";
    private static final String LOGIN_TYPE = "login_type";
    private static final String ACCOUNT_BALANCE = "account_balance";
    private static final String INSTRUCTION_PASSED = "instructions_passed";
    private static final String INSTRUCTION_DATE = "instructions_date";

    private static SharedPreferences preferences;

    static {
        preferences = GarbageCollectorApp.getAppContext().getSharedPreferences("com.example.garbagecollecto", Context.MODE_PRIVATE);
    }

    private PreferenceUtils() {
    }


    public static boolean isLocationPermitted() {
        return preferences.getBoolean("location", false);
    }

    public static void saveLocationPermitted() {
        preferences
                .edit()
                .putBoolean("location", true)
                .apply();
    }

}
