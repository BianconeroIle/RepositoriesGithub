package com.example.ilijaangeleski.repositoriesgithub.presenter;

import com.example.ilijaangeleski.repositoriesgithub.callback.GitSubscribersCallback;
import com.example.ilijaangeleski.repositoriesgithub.manager.SubscribersManager;
import com.example.ilijaangeleski.repositoriesgithub.model.GitSubscribers;
import com.example.ilijaangeleski.repositoriesgithub.view.SubscribersView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilija Angeleski on 12/7/2017.
 */

public class GitRepositoriesDetailsPresenter {

    private SubscribersManager subscribersManager;
    private WeakReference<SubscribersView> subscribersViewWeakReference;
    private List<GitSubscribers> subscribers = new ArrayList<>();

    public GitRepositoriesDetailsPresenter(
            WeakReference<SubscribersView> view,
            SubscribersManager subscribersManager
    ) {
        this.subscribersManager = subscribersManager;
        this.subscribersViewWeakReference = view;
    }

    public void fetchSubscribers(final String url) {
        subscribersManager.fetchSubscribers(url, new GitSubscribersCallback() {
            @Override
            public void onSuccess(List<GitSubscribers> response) {
                SubscribersView view = subscribersViewWeakReference.get();
                if (view != null) {
                    if (response != null) {
                        subscribers.addAll(response);
                        view.updateView(subscribers.size());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                SubscribersView view = subscribersViewWeakReference.get();
                if (view != null) {
                    view.showErrorGettingSubscribers();
                }
            }
        });
    }

    public List<GitSubscribers> getSubscribers() {
        return subscribers;
    }

    public String getRepositoriesAsJson() {
        return new Gson().toJson(subscribers);
    }

    public void loadSavedInstance(String json) {
        SubscribersView view = subscribersViewWeakReference.get();
        if (view != null) {
            List<GitSubscribers> savedRepositories = new Gson()
                    .fromJson(json, new TypeToken<List<GitSubscribers>>() {
                    }.getType());
            subscribers.addAll(savedRepositories);
            view.updateView(getSubscribers().size());
        }
    }
}
