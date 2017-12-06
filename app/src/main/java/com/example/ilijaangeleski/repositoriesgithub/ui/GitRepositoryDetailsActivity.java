package com.example.ilijaangeleski.repositoriesgithub.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ilijaangeleski.repositoriesgithub.R;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public class GitRepositoryDetailsActivity extends AppCompatActivity {
    public static final String REPOSITORY_EXTRA = "repository";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_repository_details);
    }
}
