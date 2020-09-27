package com.affinityapps.endeavor.utils;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class DatabaseUtils extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
