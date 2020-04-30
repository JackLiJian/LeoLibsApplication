package com.leo.leolibsapplication;

import android.app.Application;

import com.leo.leolibrary.utils.ActivityLifeCycle;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActivityLifeCycle lifecycleCallbacks = new ActivityLifeCycle();
        registerActivityLifecycleCallbacks(lifecycleCallbacks);

    }
}
