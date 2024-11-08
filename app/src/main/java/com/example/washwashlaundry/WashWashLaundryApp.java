package com.example.washwashlaundry;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class WashWashLaundryApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}

