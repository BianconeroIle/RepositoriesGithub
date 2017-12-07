package com.example.ilijaangeleski.repositoriesgithub.manager;

import android.util.Log;

import com.example.ilijaangeleski.repositoriesgithub.api.NetworkApi;
import com.example.ilijaangeleski.repositoriesgithub.callback.GitRepositoriesCallback;
import com.example.ilijaangeleski.repositoriesgithub.model.GitRepositories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public class RepositoriesManager {
    private NetworkApi networkApi;

    public RepositoriesManager(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public void fetchRepositories(String query, final GitRepositoriesCallback callback) {
        Call<GitRepositories> call = networkApi.fetchRepositories(NetworkApi.TOKEN, query);
        call.enqueue(new Callback<GitRepositories>() {
            @Override
            public void onResponse(Call<GitRepositories> call, Response<GitRepositories> response) {
                callback.onSuccess(response.body());
                Log.d("onTextChanged","Success");
            }

            @Override
            public void onFailure(Call<GitRepositories> call, Throwable t) {
                callback.onFailure(t);
                Log.d("onTextChanged","OnFailure");
            }
        });
    }
}
