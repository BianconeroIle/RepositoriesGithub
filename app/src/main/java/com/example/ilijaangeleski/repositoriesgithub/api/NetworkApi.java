package com.example.ilijaangeleski.repositoriesgithub.api;

import com.example.ilijaangeleski.repositoriesgithub.model.GitRepositories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public interface NetworkApi {
    String BASE_URL = "https://api.github.com/";

    @GET("search/repositories")
    Call<GitRepositories> fetchRepositories(@Query("q") String query);

}
