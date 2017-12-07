package com.example.ilijaangeleski.repositoriesgithub.api;

import com.example.ilijaangeleski.repositoriesgithub.model.GitRepositories;
import com.example.ilijaangeleski.repositoriesgithub.model.GitSubscribers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public interface NetworkApi {

    String BASE_URL = "https://api.github.com/";

    @GET("search/repositories")
    Call<GitRepositories> fetchRepositories(@Query("q") String query);

    @GET
    Call<List<GitSubscribers>> fetchSubscriptions(@Url String url);

}
