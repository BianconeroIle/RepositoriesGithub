package com.example.ilijaangeleski.repositoriesgithub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilijaangeleski.repositoriesgithub.R;
import com.example.ilijaangeleski.repositoriesgithub.model.GitSubscribers;
import com.example.ilijaangeleski.repositoriesgithub.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilija Angeleski on 12/7/2017.
 */

public class SubscribersRecyclerViewAdapter extends RecyclerView.Adapter<SubscribersRecyclerViewAdapter.MyViewHolder> {
    private List<GitSubscribers> subscribers;

    public SubscribersRecyclerViewAdapter(List<GitSubscribers> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subscriber, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GitSubscribers current = subscribers.get(position);
        holder.subscriberName.setText(current.getLogin());
        Picasso.with(holder.avatar.getContext()).load(current.getAvatarUrl())
                .transform(new CircleTransform())
                .placeholder(R.mipmap.ic_profile)
                .error(R.mipmap.ic_profile)
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return subscribers.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.subscriberName)
        TextView subscriberName;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
