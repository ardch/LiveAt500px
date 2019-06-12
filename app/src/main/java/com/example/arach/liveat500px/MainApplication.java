package com.example.arach.liveat500px;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

public class MainApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Collect application's Context when application created
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
