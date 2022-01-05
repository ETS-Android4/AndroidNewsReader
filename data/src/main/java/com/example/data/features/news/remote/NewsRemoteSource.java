package com.example.data.features.news.remote;

import com.example.data.features.news.model.Article;
import com.example.data.features.news.remote.mapper.NewsDtoToNewsMapper;
import com.example.data.remote.e.NewsApi;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class NewsRemoteSource {

    private static final String API_KEY = "67e23308dd2c4d9a96bd9f8f315ecfa7";
    private static final String EN_LANGUAGE_FILTER = "en";
    @NonNull
    private final NewsApi newsApi;

    public NewsRemoteSource(@NonNull NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    public Single<List<Article>> getNewsArticles() {
        return newsApi.getNewsArticles(API_KEY, EN_LANGUAGE_FILTER)
                .subscribeOn(Schedulers.io())
                .map(new NewsDtoToNewsMapper());
    }

}
