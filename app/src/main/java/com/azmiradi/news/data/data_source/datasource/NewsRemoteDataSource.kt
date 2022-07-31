package com.azmiradi.news.data.data_source.datasource

import com.azmiradi.news.data.model.NewsResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getNews(querySearch: String? = null,
                         country: String? = null,
                         sources: String? = null,):Response<NewsResponse>


}