package com.example.ilijaangeleski.repositoriesgithub.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilijaangeleski.repositoriesgithub.R;
import com.example.ilijaangeleski.repositoriesgithub.adapter.SubscribersRecyclerViewAdapter;
import com.example.ilijaangeleski.repositoriesgithub.model.GitRepo;
import com.example.ilijaangeleski.repositoriesgithub.presenter.GitRepositoriesDetailsPresenter;
import com.example.ilijaangeleski.repositoriesgithub.utils.CircleTransform;
import com.example.ilijaangeleski.repositoriesgithub.view.SubscribersView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public class GitRepositoryDetailsActivity extends AppCompatActivity implements SubscribersView {
    public static final String REPOSITORY_EXTRA = "repository";

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.repositoryName)
    TextView repositoryName;
    @BindView(R.id.subscribers)
    TextView subscribers;
    @BindView(R.id.listOfSubscribers)
    RecyclerView recyclerListOfSubscribers;
    private GitRepositoriesDetailsPresenter presenter;
    private GitRepo gitRepo;
    private SubscribersRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_repository_details);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null
                && getIntent().hasExtra(REPOSITORY_EXTRA)) {
            gitRepo = (GitRepo) getIntent().getExtras().getSerializable(REPOSITORY_EXTRA);
        }
        presenter = new GitRepositoriesDetailsPresenter(this);
        initView();

        if (savedInstanceState != null) {
            presenter.loadSavedInstance(savedInstanceState.getString("items"));
        } else {
            presenter.fetchSubscribers(gitRepo.getSubscribers_url());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("items", presenter.getRepositoriesAsJson());
    }

    public void initView() {
        adapter = new SubscribersRecyclerViewAdapter(presenter.getSubscribers(), this);
        repositoryName.setText(gitRepo.getName());
        Picasso.with(this).load(gitRepo.getOwner().getAvatar_url())
                .transform(new CircleTransform())
                .placeholder(R.mipmap.ic_profile)
                .error(R.mipmap.ic_profile)
                .fit()
                .into(avatar);

        RecyclerView.LayoutManager layoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = new GridLayoutManager(this, 2);
            recyclerListOfSubscribers.setLayoutManager(layoutManager);
        } else {
            layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerListOfSubscribers.setLayoutManager(layoutManager);
        }
        recyclerListOfSubscribers.setAdapter(adapter);
    }

    @Override
    public void showSubscribers(int total) {
        subscribers.setText(total+"");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorGettingSubscribers() {
        Toast.makeText(this, R.string.error_getting_subscribers, Toast.LENGTH_LONG).show();
    }
}
