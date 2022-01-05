package com.example.data.features.news.store.local.mapper;

import androidx.annotation.NonNull;

import com.example.data.features.news.local.NewsEntity;
import com.example.data.features.news.model.Article;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class NewsEntityToArticleMapper implements Function<List<NewsEntity>, List<Article>>{

    @Override
    public List<Article> apply(@NonNull List<NewsEntity> newsEntities) {
        List<Article> articles = new ArrayList<>();
        for (NewsEntity entity : newsEntities) {
            articles.add(new Article(entity.newsArticleImageURL,
                    entity.newsArticleTitle, entity.newsArticleContent, "Description"));
        }

        return articles;
    }
}
