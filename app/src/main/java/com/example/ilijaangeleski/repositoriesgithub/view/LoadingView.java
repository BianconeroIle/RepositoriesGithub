package com.example.ilijaangeleski.repositoriesgithub.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ilijaangeleski.repositoriesgithub.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilija Angeleski on 12/8/2017.
 */

public class LoadingView extends LinearLayout {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.description)
    TextView description;

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.loading_view, this);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(GONE);
    }

    public void showProgress() {
        setVisibility(VISIBLE);
        progressBar.setVisibility(VISIBLE);
        description.setVisibility(GONE);
    }

    public void showNoRepositoriesFound() {
        setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        description.setVisibility(VISIBLE);
    }

    public void hide() {
        setVisibility(GONE);
    }
}
