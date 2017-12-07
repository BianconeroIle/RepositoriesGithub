package com.example.ilijaangeleski.repositoriesgithub.di.modules;

import com.example.ilijaangeleski.repositoriesgithub.api.NetworkApi;
import com.example.ilijaangeleski.repositoriesgithub.manager.RepositoriesManager;
import com.example.ilijaangeleski.repositoriesgithub.presenter.GitRepositoriesPresenter;
import com.example.ilijaangeleski.repositoriesgithub.view.RepositoriesView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilija Angeleski on 12/7/2017.
 */
@Module
public class GitRepositoriesActivityModule {
    RepositoriesView view;

    public GitRepositoriesActivityModule(RepositoriesView view) {
        this.view = view;
    }

    @Provides
    GitRepositoriesPresenter provideGitRepositoriesPresenter(RepositoriesManager repositoriesManager) {
        return new GitRepositoriesPresenter(view, repositoriesManager);
    }

    @Provides
    RepositoriesManager providesRepositoriesManager(NetworkApi networkApi) {
        return new RepositoriesManager(networkApi);
    }
}
