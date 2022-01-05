package com.example.data;

import androidx.annotation.NonNull;

import com.example.data.features.news.local.NewsEntity;
import com.example.data.features.news.model.Article;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface NewsRepository {

    @NonNull
    Single<List<Article>> getNewsArticles();

}
