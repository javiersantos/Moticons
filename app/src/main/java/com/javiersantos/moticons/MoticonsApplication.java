package com.javiersantos.moticons;

import android.app.Application;
import android.content.Context;
import android.provider.Settings;

import com.javiersantos.moticons.utils.AppPreferences;

public class MoticonsApplication extends Application {
    private static AppPreferences appPreferences;

    @Override
    public void onCreate() {
        appPreferences = new AppPreferences(this);
        super.onCreate();
    }

    public static AppPreferences getAppPreferences() {
        return appPreferences;
    }

    public static Integer getInitialMoticoins() {
        return 40;
    }

}
