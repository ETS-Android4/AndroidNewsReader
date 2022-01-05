package com.example.data.features.news.local;

import android.util.Log;

import com.example.data.features.news.model.Article;
import com.example.data.features.news.store.local.mapper.NewsEntityToArticleMapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class NewsLocalDataStore {

    private final NewsDao dao;

    public NewsLocalDataStore(NewsDao dao) {
        this.dao = dao;
    }

    public Single<List<Article>> getNewsList() {
        return dao.queryNews().map(new NewsEntityToArticleMapper());
    }

    public Single<NewsEntity> getNewsItem(int id) {
        return dao.queryNewsItem(id);
    }

    public Completable deleteNewsItem(int id) {
        return dao.deleteNewsItem(id);
    }

    public Completable saveItem(NewsEntity newsArticle) {
        if (newsArticle.id == null) {
            return dao.insertNewsArticle(newsArticle);
        } else {
            dao.updateContent(newsArticle.newsArticleContent, newsArticle.id);
            dao.updateImageURL(newsArticle.newsArticleImageURL, newsArticle.id);
            return dao.updateTitle(newsArticle.newsArticleTitle, newsArticle.id);
        }
    }

    public void saveArticles(List<Article> articles) {
        List<NewsEntity> entities = new ArrayList<>();

        for (Article article : articles) {
            NewsEntity e = new NewsEntity();
            e.newsArticleTitle = article.title;
            e.newsArticleContent = article.content;
            e.newsArticleImageURL = article.imageUrl;
            entities.add(e);
        }

        dao.insertNewsArticles(entities)
                .observeOn(Schedulers.io())
                .subscribe();
    }
}
