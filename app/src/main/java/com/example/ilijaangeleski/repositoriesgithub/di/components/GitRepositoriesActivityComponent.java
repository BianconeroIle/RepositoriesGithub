package com.example.ilijaangeleski.repositoriesgithub.di.components;

import com.example.ilijaangeleski.repositoriesgithub.di.modules.GitRepositoriesActivityModule;
import com.example.ilijaangeleski.repositoriesgithub.ui.GitRepositoriesActivity;

import dagger.Component;

/**
 * Created by Ilija Angeleski on 12/7/2017.
 */
@Component(modules = GitRepositoriesActivityModule.class,dependencies = BaseComponent.class)
public interface GitRepositoriesActivityComponent {
    void inject(GitRepositoriesActivity activity);
}
