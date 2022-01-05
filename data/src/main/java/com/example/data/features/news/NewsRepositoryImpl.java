package com.example.data.features.news;

import com.example.data.NewsRepository;
import com.example.data.features.news.local.NewsLocalDataStore;
import com.example.data.features.news.model.Article;
import com.example.data.features.news.remote.NewsRemoteSource;
import com.example.data.features.news.store.local.mapper.NewsEntityToArticleMapper;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class NewsRepositoryImpl implements NewsRepository {

    private final NewsRemoteSource remoteSource;

    private final NewsLocalDataStore localDataStore;

    public NewsRepositoryImpl(NewsRemoteSource remoteSource, NewsLocalDataStore localDataStore) {
        this.remoteSource = remoteSource;
        this.localDataStore = localDataStore;
    }

    @Override
    @NonNull
    public Single<List<Article>> getNewsArticles() {
        return remoteSource.getNewsArticles()
                .doOnSuccess(localDataStore::saveArticles)
                .onErrorResumeNext(localDataStore.getNewsList());
    }

}
