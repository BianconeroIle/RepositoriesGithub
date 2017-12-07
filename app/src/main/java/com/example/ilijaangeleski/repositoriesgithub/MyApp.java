package com.example.ilijaangeleski.repositoriesgithub;

import android.app.Application;

import com.example.ilijaangeleski.repositoriesgithub.di.components.BaseComponent;
import com.example.ilijaangeleski.repositoriesgithub.di.components.DaggerBaseComponent;
import com.example.ilijaangeleski.repositoriesgithub.di.modules.AppModule;
import com.example.ilijaangeleski.repositoriesgithub.di.modules.NetworkModule;

/**
 * Created by Ilija Angeleski on 12/7/2017.
 */

public class MyApp extends Application {
    private BaseComponent baseComponent;
    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        baseComponent = DaggerBaseComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public static MyApp getApp() {
        return app;
    }

    public BaseComponent getBaseComponent() {
        return baseComponent;
    }
}
