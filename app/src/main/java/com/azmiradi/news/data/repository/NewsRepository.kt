package com.azmiradi.news.data.repository

import com.azmiradi.news.data.model.Article
import com.azmiradi.news.data.model.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRepository {
    suspend fun getNews(
        querySearch: String? = null,
        country: String? = null,
        sources: String? = null,
    ): Response<NewsResponse>

    fun getLocalArticle(): Flow<List<Article>>

    suspend fun deleteArticle(article: Article): Int

    suspend fun saveArticle(article: Article): Long

}