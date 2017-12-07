package com.example.ilijaangeleski.repositoriesgithub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilijaangeleski.repositoriesgithub.R;
import com.example.ilijaangeleski.repositoriesgithub.model.GitRepo;
import com.example.ilijaangeleski.repositoriesgithub.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilija Angeleski on 12/6/2017.
 */

public class RepositoryRecyclerViewAdapter extends RecyclerView.Adapter<RepositoryRecyclerViewAdapter.MyViewHolder> {
    private List<GitRepo> items;
    private int layoutResourceId;
    private OnUserItemClick listener;

    public RepositoryRecyclerViewAdapter(List<GitRepo> items, int layoutResourceId) {
        this.items = items;
        this.layoutResourceId = layoutResourceId;
    }

    public interface OnUserItemClick {
        void onRepositoryClick(GitRepo user, ImageView profileImage);
    }

    @Override
    public RepositoryRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RepositoryRecyclerViewAdapter.MyViewHolder holder, int position) {
        final GitRepo current = items.get(position);
        holder.repositoryName.setText(current.getName());
        holder.description.setText(current.getDescription());
        holder.numberForks.setText(current.getForks());
        Picasso.with(holder.avatar.getContext()).load(current.getOwner().getAvatar_url())
                .transform(new CircleTransform()).placeholder(R.mipmap.ic_profile).error(R.mipmap.ic_profile)
                .into(holder.avatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onRepositoryClick(current, holder.avatar);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.repositoryName)
        TextView repositoryName;
        @BindView(R.id.numberForks)
        TextView numberForks;
        @BindView(R.id.description)
        TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnUserItemClick(OnUserItemClick listener) {
        this.listener = listener;
    }
}