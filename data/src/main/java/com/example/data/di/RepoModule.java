package com.example.data.di;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.example.data.NewsRepository;
import com.example.data.features.news.NewsRepositoryImpl;
import com.example.data.features.news.local.NewsLocalDataStore;
import com.example.data.features.news.remote.NewsRemoteSource;
import com.example.data.features.news.store.local.NewsDatabase;
import com.example.data.remote.e.HttpClientFactory;

import io.reactivex.annotations.NonNull;

public class RepoModule {
    @NonNull
    private final Context context;

    @NonNull
    private final HttpClientFactory httpClientFactory;

    private volatile NewsDatabase database;

    public RepoModule(@NonNull Application application) {
        this.context = application.getApplicationContext();
        this.httpClientFactory = new HttpClientFactory();
    }

    public NewsRepository provideNewsRepository() {
        return new NewsRepositoryImpl(provideNewsRemoteSource(), provideLocalDataStore());
    }

    private NewsRemoteSource provideNewsRemoteSource() {
        return new NewsRemoteSource(httpClientFactory.getNewsApi());
    }

    NewsLocalDataStore provideLocalDataStore() {
        NewsDatabase database = getInstance();
        return new NewsLocalDataStore(database.newsDao());
    }

    NewsDatabase getInstance() {
        if (database == null) {
            synchronized (NewsDatabase.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context,
                            NewsDatabase.class, "Sample.db")
                            .build();
                }
            }
        }
        return database;
    }
}