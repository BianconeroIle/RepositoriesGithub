package com.example.ilijaangeleski.repositoriesgithub.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public class GitRepositories implements Serializable {

    private long total_count;
    private boolean incomplete_results;

    @SerializedName("items")
    private List<GitRepo> results;

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<GitRepo> getResults() {
        return results;
    }

    public void setResults(List<GitRepo> results) {
        this.results = results;
    }
}
