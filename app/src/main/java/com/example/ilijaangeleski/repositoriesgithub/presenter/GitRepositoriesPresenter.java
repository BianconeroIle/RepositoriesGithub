package com.example.ilijaangeleski.repositoriesgithub.presenter;

import com.example.ilijaangeleski.repositoriesgithub.callback.GitRepositoriesCallback;
import com.example.ilijaangeleski.repositoriesgithub.manager.RepositoriesManager;
import com.example.ilijaangeleski.repositoriesgithub.model.GitRepo;
import com.example.ilijaangeleski.repositoriesgithub.model.GitRepositories;
import com.example.ilijaangeleski.repositoriesgithub.view.RepositoriesView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public class GitRepositoriesPresenter {
    private RepositoriesManager repositoriesManager;
    private WeakReference<RepositoriesView> weakReferenceMainView;
    private List<GitRepo> repositories = new ArrayList<>();

    public GitRepositoriesPresenter(
            WeakReference<RepositoriesView> view,
            RepositoriesManager repositoriesManager
    ) {
        this.repositoriesManager = repositoriesManager;
        this.weakReferenceMainView = view;
    }

    public void onTextChanged(final String query) {
        repositoriesManager.fetchRepositories(query, new GitRepositoriesCallback() {
            @Override
            public void onSuccess(GitRepositories response) {
                RepositoriesView view = weakReferenceMainView.get();
                if (view != null) {
                    if (response != null) {
                        repositories.clear();
                        repositories.addAll(response.getResults());
                        view.updateView();
                    } else {
                        repositories.clear();
                        view.updateView();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                RepositoriesView view = weakReferenceMainView.get();
                if (view != null) {
                    view.showErrorGettingRepositories();
                }
            }
        });
    }

    public List<GitRepo> getRepositories() {
        return repositories;
    }

    public String getRepositoriesAsJson() {
        return new Gson().toJson(repositories);
    }

    public void loadSavedInstance(String json) {
        RepositoriesView view = weakReferenceMainView.get();
        if (view != null) {
            List<GitRepo> savedRepositories = new Gson()
                    .fromJson(json, new TypeToken<List<GitRepo>>() {
                    }.getType());
            repositories.addAll(savedRepositories);
            view.updateView();
        }
    }
}
