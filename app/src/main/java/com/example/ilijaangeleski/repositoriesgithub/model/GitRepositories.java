package com.example.ilijaangeleski.repositoriesgithub.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public class GitRepositories implements Serializable {
    @SerializedName("total_count")
    private long totalCount;
    @SerializedName("incomplete_results")
    private boolean incompleteResults;
    @SerializedName("items")
    private List<GitRepo> results;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<GitRepo> getResults() {
        return results;
    }

    public void setResults(List<GitRepo> results) {
        this.results = results;
    }
}
