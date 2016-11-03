package com.loremipsum.recifeguide;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Dannyllo on 10/09/2016.
 */
public class RecifeGuideApp extends Application {
    private static RecifeGuideApp instance;


    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    public RecifeGuideApp() {
        super();
        instance = this;
    }

    public static RecifeGuideApp getApplication() {
        if (instance == null) {
            instance = new RecifeGuideApp();
        }
        return instance;
    }
}
