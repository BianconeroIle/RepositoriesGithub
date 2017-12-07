package com.example.ilijaangeleski.repositoriesgithub.di.components;

import com.example.ilijaangeleski.repositoriesgithub.di.modules.GitRepositoriesDetailsActivityModule;
import com.example.ilijaangeleski.repositoriesgithub.ui.GitRepositoryDetailsActivity;

import dagger.Component;

/**
 * Created by Ilija Angeleski on 12/7/2017.
 */
@Component(modules = GitRepositoriesDetailsActivityModule.class,dependencies = BaseComponent.class)
public interface GitRepositoriesDetailsComponent {
    void inject(GitRepositoryDetailsActivity activity);
}
