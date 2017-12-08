package com.example.ilijaangeleski.repositoriesgithub.callback;

import com.example.ilijaangeleski.repositoriesgithub.model.GitRepositories;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public interface GitRepositoriesCallback {
    void onSuccess(GitRepositories response);

    void onFailure(Throwable t);
}
