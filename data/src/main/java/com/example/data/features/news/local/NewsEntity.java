package com.example.data.features.news.local;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class NewsEntity {

    @PrimaryKey(autoGenerate = true)
    @Nullable
    public Integer id;

    public String newsArticleTitle;
    public String newsArticleContent;
    public String newsArticleImageURL;
}
