package com.example.garbagecollector;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.garbagecollector.domain.api.StandardApiFactory;
import com.example.garbagecollector.domain.repository.RepositoryProvider;

//import com.stericson.RootTools.RootTools;

public class GarbageCollectorApp extends Application {

    private static Context sContext;

//    public static CompositeDisposable appBinds = new CompositeDisposable();

    public static void showMessage(String message) {
        Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;


        StandardApiFactory.recreate();
        RepositoryProvider.init();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }


    @NonNull
    public static Context getAppContext() {
        return sContext;
    }
}
