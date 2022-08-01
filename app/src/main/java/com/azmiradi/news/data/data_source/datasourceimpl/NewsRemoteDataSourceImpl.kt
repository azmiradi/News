package com.azmiradi.news.data.data_source.datasourceimpl

import com.azmiradi.news.data.data_source.datasource.NewsRemoteDataSource
import com.azmiradi.news.data.model.NewsResponse
import com.azmiradi.news.data.network.ApiServices
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val apiServices: ApiServices
) : NewsRemoteDataSource {

    override suspend fun getNews(
        querySearch: String?,
        country: String?,
        sources: String?
    ): Response<NewsResponse> {
       return apiServices.getNews(querySearch, country, sources)
    }
}