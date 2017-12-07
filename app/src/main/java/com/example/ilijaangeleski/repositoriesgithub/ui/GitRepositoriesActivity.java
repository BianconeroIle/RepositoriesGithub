package com.example.ilijaangeleski.repositoriesgithub.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ilijaangeleski.repositoriesgithub.MyApp;
import com.example.ilijaangeleski.repositoriesgithub.R;
import com.example.ilijaangeleski.repositoriesgithub.adapter.RepositoryRecyclerViewAdapter;
import com.example.ilijaangeleski.repositoriesgithub.di.components.DaggerGitRepositoriesActivityComponent;
import com.example.ilijaangeleski.repositoriesgithub.di.modules.GitRepositoriesActivityModule;
import com.example.ilijaangeleski.repositoriesgithub.model.GitRepo;
import com.example.ilijaangeleski.repositoriesgithub.presenter.GitRepositoriesPresenter;
import com.example.ilijaangeleski.repositoriesgithub.view.RepositoriesView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitRepositoriesActivity extends AppCompatActivity implements RepositoriesView {
    public static final String ITEMS = "items";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.searchRepository)
    EditText searchRepository;

    @Inject
    GitRepositoriesPresenter presenter;

    private RepositoryRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);
        createDependencies();
        initView();
        initListeners();

        if (savedInstanceState != null) {
            presenter.loadSavedInstance(savedInstanceState.getString(ITEMS));
        }
    }

    private void createDependencies() {
        DaggerGitRepositoriesActivityComponent
                .builder()
                .gitRepositoriesActivityModule(new GitRepositoriesActivityModule(this))
                .baseComponent(MyApp.getApp().getBaseComponent())
                .build()
                .inject(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ITEMS, presenter.getRepositoriesAsJson());
    }

    @Override
    public void updateView() {
        adapter.notifyDataSetChanged();
    }

    public void initView() {
        adapter = new RepositoryRecyclerViewAdapter(
                presenter.getRepositories(),
                R.layout.item_repository
        );
        RecyclerView.LayoutManager layoutManager;
        if (isLandscape()) {
            layoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(layoutManager);
        } else {
            layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
        }
        recyclerView.setAdapter(adapter);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public void initListeners() {
        adapter.setOnUserItemClick(new RepositoryRecyclerViewAdapter.OnUserItemClick() {
            @Override
            public void onRepositoryClick(GitRepo repo, ImageView avatar) {
                Log.d("", "onRepositoryClick :" + repo);
                openGitDetailsActivity(repo, avatar);
            }
        });

        searchRepository.addTextChangedListener(new TextWatcher() {
            CountDownTimer timer = null;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (timer != null) {
                    timer.cancel();
                }
                timer = new CountDownTimer(500, 250) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        presenter.onTextChanged(searchRepository.getText().toString());
                    }
                }.start();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void openGitDetailsActivity(GitRepo repo, ImageView avatar) {
        Intent intent = new Intent(GitRepositoriesActivity.this, GitRepositoryDetailsActivity.class);
        intent.putExtra(GitRepositoryDetailsActivity.REPOSITORY_EXTRA, repo);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(GitRepositoriesActivity.this, avatar, "profile");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void showErrorGettingRepositories() {
        Toast.makeText(this, R.string.error_getting_repositories, Toast.LENGTH_LONG).show();
    }
}
