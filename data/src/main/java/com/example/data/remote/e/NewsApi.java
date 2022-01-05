package com.example.data.remote.e;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.data.features.news.remote.model.ArticleListDto;


public interface NewsApi {
    @GET("/v2/top-headlines")
    Single<ArticleListDto> getNewsArticles(@Query("apiKey") String apiKey, @Query("language") String language);

}
