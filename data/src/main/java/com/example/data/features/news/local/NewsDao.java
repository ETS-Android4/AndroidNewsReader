package com.example.data.features.news.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    Single<List<NewsEntity>> queryNews();

    @Query("SELECT * FROM news where id= :id")
    Single<NewsEntity> queryNewsItem(int id);

    @Query("DELETE FROM news where id=:id")
    Completable deleteNewsItem(int id);

    @Query("DELETE FROM news")
    Completable deleteAllNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertNewsArticles(List<NewsEntity> entities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertNewsArticle(NewsEntity entity);

    @Query("UPDATE news SET newsArticleTitle = :newsArticleTitle where id=:id")
    Completable updateTitle(String newsArticleTitle, int id);

    @Query("UPDATE news SET newsArticleContent = :newsArticleContent where id=:id")
    Completable updateContent(String newsArticleContent, int id);

    @Query("UPDATE news SET newsArticleImageURL = :newsArticleImageURL where id=:id")
    Completable updateImageURL(String newsArticleImageURL, int id);

}
