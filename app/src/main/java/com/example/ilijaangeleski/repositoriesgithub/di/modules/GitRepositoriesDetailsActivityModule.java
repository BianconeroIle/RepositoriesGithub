package com.example.ilijaangeleski.repositoriesgithub.di.modules;

import com.example.ilijaangeleski.repositoriesgithub.api.NetworkApi;
import com.example.ilijaangeleski.repositoriesgithub.manager.SubscribersManager;
import com.example.ilijaangeleski.repositoriesgithub.presenter.GitRepositoriesDetailsPresenter;
import com.example.ilijaangeleski.repositoriesgithub.view.SubscribersView;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilija Angeleski on 12/7/2017.
 */
@Module
public class GitRepositoriesDetailsActivityModule {
    WeakReference<SubscribersView> view;

    public GitRepositoriesDetailsActivityModule(SubscribersView view) {
        this.view = new WeakReference<>(view);
    }

    @Provides
    GitRepositoriesDetailsPresenter providesDetailsPresenter(SubscribersManager subscribersManager){
        return new GitRepositoriesDetailsPresenter(view,subscribersManager);
    }

    @Provides
    SubscribersManager providesSubscribersManager(NetworkApi networkApi){
        return new SubscribersManager(networkApi);
    }
}
