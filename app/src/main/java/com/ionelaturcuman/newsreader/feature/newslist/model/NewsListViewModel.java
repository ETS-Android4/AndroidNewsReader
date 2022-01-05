package com.ionelaturcuman.newsreader.feature.newslist.model;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.data.NewsRepository;
import com.ionelaturcuman.newsreader.feature.newslist.model.mapper.NewsArticlesToViewModelMapper;
import com.ionelaturcuman.newsreader.reactive.SingleLiveEvent;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class NewsListViewModel extends AndroidViewModel implements LifecycleObserver, OnArticleSelectedListener {

    private final static String LINK = "https://newsapi.org/";
    private final NewsRepository repo;

    public final ObservableBoolean isLoading;
    public final ObservableField<String> resultText;
    public final SingleLiveEvent<Throwable> error;
    public final SingleLiveEvent<String> openLink;

    public final ObservableList<ArticleItemViewModel> items;

    public NewsListViewModel(Application application, NewsRepository repo) {
        super(application);
        this.items = new ObservableArrayList<>();
        this.repo = repo;
        this.isLoading = new ObservableBoolean();
        this.resultText = new ObservableField<>();
        this.error = new SingleLiveEvent<>();
        this.openLink = new SingleLiveEvent<>();
    }

    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void refreshData() {
        isLoading.set(true);
        repo.getNewsArticles()
                .map(new NewsArticlesToViewModelMapper())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onNewsArticlesReceived,
                        this::onNewsArticlesError
                );

    }

    private void onNewsArticlesReceived(@NonNull List<ArticleItemViewModel> articles) {
        isLoading.set(false);
        resultText.set("Results received!");

        items.clear();
        items.addAll(articles);
    }

    private void onNewsArticlesError(Throwable throwable) {
        isLoading.set(false);
        error.setValue(throwable);
    }

}