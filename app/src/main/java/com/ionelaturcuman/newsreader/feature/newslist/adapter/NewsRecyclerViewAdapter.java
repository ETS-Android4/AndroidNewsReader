package com.ionelaturcuman.newsreader.feature.newslist.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ionelaturcuman.newsreader.databinding.NewsItemBinding;
import com.ionelaturcuman.newsreader.feature.newslist.model.ArticleItemViewModel;
import com.ionelaturcuman.newsreader.feature.newslist.model.OnArticleSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.TaskViewHolder> {

    private List<ArticleItemViewModel> modelList;
    private final OnArticleSelectedListener handler;

    public NewsRecyclerViewAdapter(OnArticleSelectedListener handler) {
        this.handler = handler;
        this.modelList = new ArrayList<>();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemBinding binder = NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new TaskViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.binding.setViewModel(modelList.get(position));
        holder.binding.setHandler(handler);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setItems(List<ArticleItemViewModel> items) {
        this.modelList = items;
        notifyDataSetChanged();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        final NewsItemBinding binding;

        public TaskViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
