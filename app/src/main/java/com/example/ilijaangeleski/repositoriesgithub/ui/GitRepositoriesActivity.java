package com.example.ilijaangeleski.repositoriesgithub.ui;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ilijaangeleski.repositoriesgithub.R;
import com.example.ilijaangeleski.repositoriesgithub.adapter.RepositoryRecyclerViewAdapter;
import com.example.ilijaangeleski.repositoriesgithub.model.GitRepo;
import com.example.ilijaangeleski.repositoriesgithub.presenter.GitRepositoriesPresenter;
import com.example.ilijaangeleski.repositoriesgithub.view.RepositoriesView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitRepositoriesActivity extends AppCompatActivity implements RepositoriesView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.searchRepository)
    EditText searchRepository;
    GitRepositoriesPresenter presenter;
    private RepositoryRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);
        presenter = new GitRepositoriesPresenter(this);
        initVariables();
        initListeners();
    }

    @Override
    public void showRepositories() {
        adapter.notifyDataSetChanged();
    }

    public void initVariables() {
        adapter = new RepositoryRecyclerViewAdapter(presenter.getRepositories(), getApplicationContext(), R.layout.item_repository);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void initListeners() {
        adapter.setOnUserItemClick(new RepositoryRecyclerViewAdapter.OnUserItemClick() {
            @Override
            public void onRepositoryClick(GitRepo repo, ImageView avatar) {
                Log.d("", "onRepositoryClick :" + repo);
                Intent intent = new Intent(GitRepositoriesActivity.this, GitRepositoryDetailsActivity.class);
                intent.putExtra(GitRepositoryDetailsActivity.REPOSITORY_EXTRA, repo);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(GitRepositoriesActivity.this, avatar, "profile");
                startActivity(intent, options.toBundle());
            }
        });
        searchRepository.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onTextChanged(editable.toString());
            }
        });
    }

    @Override
    public void showErrorGettingRepositories() {
        Toast.makeText(this, R.string.error_getting_repositories, Toast.LENGTH_LONG).show();
    }
}
