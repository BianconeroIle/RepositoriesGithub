package com.example.ilijaangeleski.repositoriesgithub.manager;

import android.util.Log;

import com.example.ilijaangeleski.repositoriesgithub.api.NetworkApi;
import com.example.ilijaangeleski.repositoriesgithub.callback.GitSubscribersCallback;
import com.example.ilijaangeleski.repositoriesgithub.model.GitSubscribers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ilija Angeleski on 12/7/2017.
 */

public class SubscribersManager {
    private NetworkApi networkApi;

    public SubscribersManager(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public void fetchSubscribers(String url, final GitSubscribersCallback callback) {
        Call<List<GitSubscribers>> call = networkApi.fetchSubscriptions(url);
        call.enqueue(new Callback<List<GitSubscribers>>() {
            @Override
            public void onResponse(Call<List<GitSubscribers>> call, Response<List<GitSubscribers>> response) {
                Log.d("fetchSubscribers", "onResponse");
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<GitSubscribers>> call, Throwable t) {
                Log.d("fetchSubscribers", "onFailure");
                callback.onFailure(t);
            }
        });
    }
}
