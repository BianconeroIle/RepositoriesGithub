package com.example.ilijaangeleski.repositoriesgithub.di.components;

import android.content.Context;

import com.example.ilijaangeleski.repositoriesgithub.api.NetworkApi;
import com.example.ilijaangeleski.repositoriesgithub.di.modules.AppModule;
import com.example.ilijaangeleski.repositoriesgithub.di.modules.NetworkModule;

import dagger.Component;

/**
 * Created by Ilija Angeleski on 12/7/2017.
 */
@Component(modules = {NetworkModule.class, AppModule.class})
public interface BaseComponent {
    Context getContext();

    NetworkApi getNetworkApi();
}
