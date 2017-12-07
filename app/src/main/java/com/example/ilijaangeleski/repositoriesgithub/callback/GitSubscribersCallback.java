package com.example.ilijaangeleski.repositoriesgithub.callback;

import com.example.ilijaangeleski.repositoriesgithub.model.GitSubscribers;

import java.util.List;

/**
 * Created by Ilija Angeleski on 12/7/2017.
 */

public interface GitSubscribersCallback {
    void onSuccess(List<GitSubscribers> response);
    void onFailure(Throwable t);
}
