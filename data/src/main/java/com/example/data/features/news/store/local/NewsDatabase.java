package com.example.data.features.news.store.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.data.features.news.local.NewsDao;
import com.example.data.features.news.local.NewsEntity;

@Database(entities = {NewsEntity.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();

}
