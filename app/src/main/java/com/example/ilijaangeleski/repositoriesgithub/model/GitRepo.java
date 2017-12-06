package com.example.ilijaangeleski.repositoriesgithub.model;

import java.io.Serializable;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public class GitRepo implements Serializable{

    private String name;
    private String full_name;
    private String description;
    private String forks;
    private String forks_url;
    private Owner owner;

    public GitRepo(String name, String full_name, String description, String forks, String forks_url, Owner owner) {
        this.name = name;
        this.full_name = full_name;
        this.description = description;
        this.forks = forks;
        this.forks_url = forks_url;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getForks_url() {
        return forks_url;
    }

    public void setForks_url(String forks_url) {
        this.forks_url = forks_url;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
