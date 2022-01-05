package com.ionelaturcuman.newsreader.bindings;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ionelaturcuman.newsreader.feature.newslist.model.ArticleItemViewModel;
import com.ionelaturcuman.newsreader.feature.newslist.model.OnArticleSelectedListener;
import com.ionelaturcuman.newsreader.feature.newslist.adapter.NewsRecyclerViewAdapter;

import java.util.List;

public class RecyclerBindings {

    @BindingAdapter({"items", "handler"})
    public static void setItems(RecyclerView recyclerView,
                                List<ArticleItemViewModel> tasks, OnArticleSelectedListener handler) {
        NewsRecyclerViewAdapter taskAdapter = (NewsRecyclerViewAdapter) recyclerView.getAdapter();
        if (taskAdapter == null) {
            taskAdapter = new NewsRecyclerViewAdapter(handler);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(taskAdapter);
        }
        taskAdapter.setItems(tasks);
    }
}
