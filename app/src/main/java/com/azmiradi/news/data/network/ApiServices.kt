package com.azmiradi.news.data.network

import com.azmiradi.news.BuildConfig
import com.azmiradi.news.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") querySearch: String?=null,
        @Query("country") country: String?=null,
        @Query("sources") sources: String?=null,
        @Query("apiKey") apiKey: String= BuildConfig.NEWS_KEY
    ): Response<NewsResponse>


}